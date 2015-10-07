#lang racket

(define (last-elem l)
  (if (null? l) "no last element"
    (if (null? (cdr l)) (car l)
        (last-elem (cdr l))
)))

(last-elem '(1 2 3 4))

(define (my-append l n)
  (if (null? l) (list n)
    (cons (car l)
          (my-append (cdr l) n)))
)

(my-append '(1 2 3) #t)


(define (gen-list n)
  (if (integer? n)
      (if (= n 0) '() (my-append (gen-list (- n 1)) n))
      "nu"
  )
)

(gen-list #t)

(define (rev l)
  (if (null? l) '()
       (my-append (rev (cdr l)) (car l))
    )
)

(rev '(1 2 3))

(define (lookup l y)
  (if (null? l) #f
    (if (eq? y (car l)) (car (cdr l))
        (lookup (cddr l) y)
    )
   )
)

(lookup '(x 1 z 2 q 3) 'q)


(define env `((plus ,+ x 3 y 4)
              (times ,* z 6)
              (m 4 z 5))
)

(define compute())
