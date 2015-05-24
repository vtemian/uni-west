#lang racket

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

(define T1 '((0 1 1) (0 1 1) (1 0 0)))
(define T2 '((1 0 0) (0 1 0) (1 0 1)))

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

(sumTo T1 3)