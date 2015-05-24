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

(define (transpose-simple arr)
  (apply map list arr))

(define (sum arr) (foldr + 0 arr))

(define (& x y) 
  (if (and (eq? x y) (eq? x 1)) 1 0))

(define (|| x y) 
  (if (or (eq? x 1) (eq? y 1)) 1 0))

(define (transpose-recv arr)
  (if (eq? (sum (map length arr)) 0) '()
     (append (list (map car arr)) (transpose-recv (map cdr arr)))    
  ))

(define (boolSum-row a b r)
  (append r (list(foldr (lambda (x y r2)
                   (cons (|| x y) r2)) '() a b))))
(define (boolSum A B)
   (foldl boolSum-row '() A B)
)

(define (boolProd A B)
   (foldl (lambda (a r1)
            (append r1 (list (foldr (lambda (x r2)
                                      (cons (foldl (lambda (u i r) (|| r (& u i))) 0 a x) r2)) 
                              '() (transpose-recv B))))
           ) 
    '() A)
)

(define (sumTo A k)
  (if (eq? k 1)  A
      (boolSum A (boolProd A (sumTo A (- k 1))))))

(define (sumOne A)
   (if (eq? (length (car A)) 1) (caar A)
   (+ (caar A) (sumOne (map cdr (cdr A)))))
)

(define (hasOne? A)
 (eq? (sumOne A) (length A)) #t #f)

(define (hasCycle? G)
  (hasOne? (sumTo (adj G) (length (graph-nodes G))))
)

(hasCycle? G1)