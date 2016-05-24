# Javier Civera, jcivera@unizar.es
# December 2015
# Spam filtering using Naive Bayes

######################################################
# Imports
######################################################

import matplotlib.pyplot as plt
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.naive_bayes import MultinomialNB
from sklearn.naive_bayes import BernoulliNB
import numpy as np
import json
import glob
from sklearn import metrics
import random

######################################################
# Aux. functions
######################################################

# load_enron_folder: load training, validation and test sets from an enron path
def load_enron_folder(path):

   ### Load ham mails ###

   # List mails in folder
   ham_folder = path + '/ham/*.txt'
   ham_list = glob.glob(ham_folder)
   num_ham_mails = len(ham_list)

   ham_mail = []
   for i in range(0,num_ham_mails-1):
      ham_i_path = ham_list[i]
      print(ham_i_path)
      # Open file
      ham_i_file = open(ham_i_path, 'r')
      # Read
      ham_i_str = ham_i_file.read()
      # Convert to Unicode
      ham_i_text = ham_i_str.decode('utf-8',errors='ignore')
      # Append to the mail structure
      ham_mail.append(ham_i_text)
      # Close file
      ham_i_file.close()
   
   random.shuffle(ham_mail)

   # Load spam mails

   spam_folder = path + '/spam/*.txt'
   spam_list = glob.glob(spam_folder)
   num_spam_mails = len(spam_list)

   spam_mail = []
   for i in range(0,num_spam_mails-1):
      spam_i_path = spam_list[i]
      print(spam_i_path)
      # Open file
      spam_i_file = open(spam_i_path, 'r')
      # Read
      spam_i_str = spam_i_file.read()
      # Convert to Unicode
      spam_i_text = spam_i_str.decode('utf-8',errors='ignore')
      # Append to the mail structure
      spam_mail.append(spam_i_text)
      # Close file
      spam_i_file.close()

   random.shuffle(spam_mail)

   # Separate into training, validation and test
   num_ham_training = int(round(0.8*num_ham_mails))
   ham_training_mail = ham_mail[0:num_ham_training]
   #print(num_ham_training)
   #print(len(ham_training_mail))
   ham_training_labels = [0]*num_ham_training
   #print(len(ham_training_labels))

   num_ham_validation = int(round(0.1*num_ham_mails))
   ham_validation_mail = ham_mail[num_ham_training:num_ham_training+num_ham_validation]
   ham_validation_labels = [0] * num_ham_validation

   ham_test_mail = ham_mail[num_ham_training+num_ham_validation:num_ham_mails]
   ham_test_labels = [0] * (num_ham_mails-num_ham_training-num_ham_validation-1)

   num_spam_training = int(round(0.8*num_spam_mails))
   spam_training_mail = spam_mail[0:num_spam_training]
   spam_training_labels = [1]*num_spam_training
   #print(num_spam_training)
   #print(len(spam_training_mail))
   #print(len(spam_training_labels))

   num_spam_validation = int(round(0.1*num_spam_mails))
   spam_validation_mail = spam_mail[num_spam_training:num_spam_training+num_spam_validation]
   spam_validation_labels = [1] * num_spam_validation

   spam_test_mail = spam_mail[num_spam_training+num_spam_validation:num_spam_mails]
   spam_test_labels = [1] * (num_spam_mails-num_spam_training-num_spam_validation-1)

   training_mails = ham_training_mail + spam_training_mail
   training_labels = ham_training_labels + spam_training_labels
   validation_mails = ham_validation_mail + spam_validation_mail
   validation_labels = ham_validation_labels + spam_validation_labels
   test_mails = ham_test_mail + spam_test_mail
   test_labels = ham_test_labels + spam_test_labels
   #print("--")
   #print(len(training_labels))
   #print(len(training_mails))
   #print("--")
   #print(len(validation_labels))
   #print(len(validation_mails))
   #print("--")
   #print(len(test_labels))
   #print(len(test_mails))
   data = {'training_mails': training_mails, 'training_labels': training_labels, 'validation_mails': validation_mails, 'validation_labels': validation_labels, 'test_mails': test_mails, 'test_labels': test_labels} 

   return data


######################################################
# Main
######################################################

#print("Starting...")

# Path to the folder containing the mails
#folder_enron1 = r'C:\Users\jcivera\Dropbox\spam_filtering_ia\datasets\enron-spam\enron1'
# Load mails
#data1 = load_enron_folder(folder_enron1)
#folder_enron2 = r'C:\Users\jcivera\Dropbox\spam_filtering_ia\datasets\enron-spam\enron2'
# Load mails
#data2 = load_enron_folder(folder_enron2)
#training_mails = data1['training_mails']+data2['training_mails']
#training_labels = data1['training_labels']+data2['training_labels']
#test_mails = data1['test_mails']+data2['test_mails']
#test_labels = data1['test_labels']+data2['test_labels']
