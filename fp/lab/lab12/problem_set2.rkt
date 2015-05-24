#lang racket

(define (transpose-simple arr)
  (apply map list arr))


; (transpose '((1 2 3) (4 5 6) (7 8 9)))
