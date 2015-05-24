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
  (map (lambda (x) (map (lambda (y) 
                          (build-adj y (outgoing G x)))
       (graph-nodes G))
)