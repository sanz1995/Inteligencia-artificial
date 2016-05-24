# Jorge Sanz, 680182
# December 2015
# Spam filtering using Naive Bayes

######################################################
# Imports
######################################################

from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.naive_bayes import BernoulliNB
from sklearn.metrics import confusion_matrix
from sklearn.metrics import precision_recall_curve
from sklearn.metrics import f1_score
import matplotlib.pyplot as plt
import numpy as np
import load_mails_example


######################################################
# Aux. functions
######################################################

def plot_confusion_matrix(cm, title='Confusion matrix', cmap=plt.cm.Blues):
	plt.clf()
	plt.imshow(cm, interpolation='nearest', cmap=cmap)
	plt.title(title)
	plt.colorbar()
	np.set_printoptions(precision=2)
	tick_marks = np.arange(2)
	plt.xticks(tick_marks, ('ham','spam'))
	plt.yticks(tick_marks, ('ham','spam'))
	plt.tight_layout()	
	plt.ylabel('True label')
	plt.xlabel('Predicted label')
	plt.show()

def plot_precision_recall(pr):
	plt.clf()
	plt.plot(pr[1], pr[0], label='Precision-Recall curve')
	plt.xlabel('Recall')
	plt.ylabel('Precision')
	plt.ylim([0.0, 1.05])
	plt.xlim([0.0, 1.0])
	plt.title('Precision-Recall')
	plt.legend(loc="lower left")
	plt.show()

def porcentaje(predicted,labels):
	aciertos=0.0
	for n in range(0,len(labels)-1):
		if predicted[n] == validation_labels[n]:
			aciertos=aciertos + 1.0

	return	aciertos/len(labels)


######################################################
# Main
######################################################



print("Starting...")

# Path to the folder containing the mails
folder_enron1 = r'/home/jorge/Uni/IA/trabajo/data/enron1'
# Load mails
data1 = load_mails_example.load_enron_folder(folder_enron1)
folder_enron2 = r'/home/jorge/Uni/IA/trabajo/data/enron2'
# Load mails
data2 = load_mails_example.load_enron_folder(folder_enron2)
folder_enron3 = r'/home/jorge/Uni/IA/trabajo/data/enron3'
# Load mails
data3 = load_mails_example.load_enron_folder(folder_enron3)
folder_enron4 = r'/home/jorge/Uni/IA/trabajo/data/enron4'
# Load mails
data4 = load_mails_example.load_enron_folder(folder_enron4)
folder_enron5 = r'/home/jorge/Uni/IA/trabajo/data/enron5'
# Load mails
data5 = load_mails_example.load_enron_folder(folder_enron5)
folder_enron6 = r'/home/jorge/Uni/IA/trabajo/data/enron6'
# Load mails
data6 = load_mails_example.load_enron_folder(folder_enron6)

training_mails = data1['training_mails']+data2['training_mails']+data3['training_mails']+data4['training_mails']+data5['training_mails']+data6['training_mails']
training_labels = data1['training_labels']+data2['training_labels']+data3['training_labels']+data4['training_labels']+data5['training_labels']+data6['training_labels']
validation_mails = data1['validation_mails']+data2['validation_mails']+data3['validation_mails']+data4['validation_mails']+data5['validation_mails']+data6['validation_mails']
validation_labels = data1['validation_labels']+data2['validation_labels']+data3['validation_labels']+data4['validation_labels']+data5['validation_labels']+data6['validation_labels']
test_mails = data1['test_mails']+data2['test_mails']+data3['test_mails']+data4['test_mails']+data5['test_mails']+data6['test_mails']
test_labels = data1['test_labels']+data2['test_labels']+data3['test_labels']+data4['test_labels']+data5['test_labels']+data6['test_labels']




#print("-----------------------------------")
training_validation_mails=validation_mails + training_mails
training_validation_labels=validation_labels + training_labels

tam=len(training_validation_mails)
tam_validation=len(validation_mails)
#print(len(validation_labels))
#print(len(validation_mails))
#print("--")
k=5
for i in range(1,k):
	inferior=(i*tam_validation)%tam
	superior=(i*tam_validation + tam_validation)%tam
	if superior < inferior:
		inferior=0
		superior=tam_validation
	validation_mails = validation_mails + training_validation_mails[inferior:superior-1]
	validation_labels = validation_labels + training_validation_labels[inferior:superior-1]
	training_mails = training_mails + training_validation_mails[0:inferior] + training_validation_mails[superior:tam]
	training_labels = training_labels + training_validation_labels[0:inferior] + training_validation_labels[superior:tam]
	#print(len(validation_labels))
	#print(len(validation_mails))
	#print("--")

cv=CountVectorizer()
cv=cv.fit(training_mails)
X=cv.transform(training_mails)
Z=cv.transform(validation_mails)


mejor = 0.0
suavizado = 0
for j in range(1,5):
	clf = BernoulliNB(j).fit(X, training_labels)
	#clf = MultinomialNB(j).fit(X, training_labels)
	res=clf.predict(Z)

	porAcierto=porcentaje(res,validation_labels)
	if mejor < porAcierto:
		mejor = porAcierto
		suavizado = j
	#print(porAcierto)

clf = MultinomialNB(suavizado).fit(X, training_labels)
Q=cv.transform(test_mails)
res=clf.predict(Q)
#res2=clf.predict_proba(Q)

print('----------')
print "porcentaje %.4f" % porcentaje(res,test_labels)
print "mejor suavizado %d" % suavizado
print('----------')



#Matriz de confusiones
cm = confusion_matrix(test_labels,res)
plot_confusion_matrix(cm)

#Curva precision-recall
pr = precision_recall_curve(test_labels,res)
plot_precision_recall(pr)


f1 = f1_score(test_labels,res)
print "f1-score %.4f" % f1




