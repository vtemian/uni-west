#lang racket

(require racket/format)

(define (collapseRow row)
    (append (list (get-value row)) (put-zeros (collapseRow-acc row) 4))
)

(define (put-zeros l n)
    (if (< (length l) n)
        (put-zeros (append l '(0)) n)
        l
    )
)

(define (skip n l)
  (cond
    [(= n 0) l]
    [else (skip (- n 1) (cdr l))]
  )
)

(define (caar l)
  (car (cdr l))
)

(define (get-value row)
    (cond
        [(<= (length row) 1) 0]
        [(= (car row) 0) (get-value (cdr row))]
        [(= (car row) (caar row))
         (+ (* 2 (car row)) (get-value (skip 2 row)))]
        [else (get-value (cdr row))]
    )
)

(define (collapseRow-acc row)
    (cond
        [(<= (length row) 1) row]
        [(= (car row) 0) (collapseRow-acc (cdr row))]
        [(= (car row) (caar row))
         (cons (* 2 (car row)) (collapseRow-acc (skip 2 row)))]
        [else (cons (car row) (collapseRow-acc (cdr row)))]
    )
)

(collapseRow '(2 4 256 2))
