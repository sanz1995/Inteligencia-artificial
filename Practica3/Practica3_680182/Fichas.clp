;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;           MODULO MAIN           ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmodule MAIN 
	(export deftemplate nodo)
	(export deffunction heuristica)
	)

(deftemplate MAIN::nodo
   (multislot estado)
   (multislot camino)
   (slot heuristica)
   (slot clase (default abierto)))

(defglobal MAIN
   ?*estado-inicial* = (create$ 1 1 1 H 0 0 0)
   ?*estado-final* = (create$ 0 0 0 H 1 1 1))

(deffunction MAIN::heuristica ($?estado)
   (bind ?res 0)
   (loop-for-count (?i 1 7)
    (if (neq (nth ?i $?estado)
             (nth ?i ?*estado-final*))
         then (bind ?res (+ ?res 1))
     )
    )
   ?res)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;     MODULO MAIN::INICIAL        ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defrule MAIN::inicial
   =>
   (assert (nodo 
              (estado ?*estado-inicial*)
              (camino)
              (heuristica (heuristica ?*estado-inicial*))
              (clase abierto)))
 )


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; MODULO MAIN::CONTROL	    ;;;
;;; PRIMERO EL MEJOR                ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defrule MAIN::pasa-el-mejor-a-cerrado
   ?nodo <- (nodo (clase abierto) 
                  (heuristica ?h1))
   (not (nodo (clase abierto)
              (heuristica ?h2&:(< ?h2 ?h1))))
=> 
   (modify ?nodo (clase cerrado))
   (focus OPERADORES)
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; MODULO OPERACIONES              ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmodule OPERADORES
 (import MAIN deftemplate nodo)
 (import MAIN deffunction heuristica)) 


(defrule OPERADORES::izq1
   (nodo (estado $?a ?b H $?c)
          (camino $?movimientos)
          (clase cerrado))
=>
   (bind $?nuevo-estado (create$  $?a H ?b $?c))
   (assert (nodo 
		      (estado $?nuevo-estado)
              (camino $?movimientos izq1)
              (heuristica (heuristica $?nuevo-estado)))))

(defrule OPERADORES::izq2
   (nodo (estado $?a ?b ?c H $?d)
          (camino $?movimientos)
          (clase cerrado))
=>
   (bind $?nuevo-estado (create$ $?a H ?c ?b $?d))
   (assert (nodo 
              (estado $?nuevo-estado)
              (camino $?movimientos izq2)
              (heuristica (heuristica $?nuevo-estado)))))
              
(defrule OPERADORES::izq3
   (nodo (estado $?a ?b ?c ?d H $?e)
          (camino $?movimientos)
          (clase cerrado))
=>
   (bind $?nuevo-estado (create$ $?a H ?c ?d ?b $?e))
   (assert (nodo 
		      (estado $?nuevo-estado)
              (camino $?movimientos izq3)
              (heuristica (heuristica $?nuevo-estado)))))


(defrule OPERADORES::dcha1
   (nodo (estado $?a H ?b $?c)
          (camino $?movimientos)
          (clase cerrado))
 =>
   (bind $?nuevo-estado (create$ $?a ?b H $?c))
   (assert (nodo 
		      (estado $?nuevo-estado)
              (camino $?movimientos dcha1)
              (heuristica (heuristica $?nuevo-estado)))))

(defrule OPERADORES::dcha2
   (nodo (estado $?a H ?b ?c $?d)
          (camino $?movimientos)
          (clase cerrado))
 =>
   (bind $?nuevo-estado (create$ $?a ?c ?b H $?d))
   (assert (nodo 
		      (estado $?nuevo-estado)
              (camino $?movimientos dcha2)
              (heuristica (heuristica $?nuevo-estado)))))

(defrule OPERADORES::dcha3
   (nodo (estado $?a H ?b ?c ?d $?e)
          (camino $?movimientos)
          (clase cerrado))
 =>
   (bind $?nuevo-estado (create$ $?a ?d ?b ?c H $?e))
   (assert (nodo 
		      (estado $?nuevo-estado)
              (camino $?movimientos dcha3)
              (heuristica (heuristica $?nuevo-estado)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;      MODULO RESTRICCIONES       ;;;
;;; 			         ...;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmodule RESTRICCIONES
   (import MAIN deftemplate nodo))

(defrule RESTRICCIONES::repeticiones-de-nodo
   (declare (auto-focus TRUE))
   (nodo (estado $?actual)
         (camino $?movimientos-1))
   ?nodo <- (nodo (estado $?actual)
                  (camino $?movimientos-2&: (> (length $?movimientos-2)(length $?movimientos-1))))
 =>
   (retract ?nodo))
   
  

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;    MODULO MAIN::SOLUCION        ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmodule SOLUCION
   (import MAIN deftemplate nodo))

(defrule SOLUCION::reconoce-solucion
   (declare (auto-focus TRUE))
   ?nodo <- (nodo (estado 0 0 0 H 1 1 1)
               (camino $?movimientos))
 => 
   (retract ?nodo)
   (assert (solucion $?movimientos)))

(defrule SOLUCION::escribe-solucion
   (solucion $?movimientos)
  =>
   (printout t "Solucion:" $?movimientos crlf)
   (halt))

