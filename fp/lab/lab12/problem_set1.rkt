#lang racket

(struct graph (nodes edges))
(define G1 (graph '(a b c d e f g)
                  '((a b) (c b) (c d) (d a) (d e) (d f)
                          (e g) (f e) (g b) (g c) (g f))))
(define (outgoing G v)
 (map cadr (filter (lambda (e) (eq? (first e) v))(graph-edges G))))

(define (incoming G v)
 (map first (filter (lambda (e) (eq? (second e) v))(graph-edges G))))

(define (inDegree G v)
  (length (incoming G v)))

(define (outDegree G v)
  (length (outgoing G v)))


(define (adj G)
  (foldl (lambda (x r1)
           (append r1
                   (list (foldl (lambda (y r2)
               (if (member (list x y) (graph-edges G)) (append r2 (list 1))
                   (append r2 (list 0)))) '() (graph-nodes G) ))
            )
         )  '() (graph-nodes G))
)
