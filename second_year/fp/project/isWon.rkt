#lang racket

(struct Game (board score) #:transparent)

(define (isWon? game)
  (= (apply max (car (Game-board game))) 2048))


(isWon? (Game '((4 8 16 2) (0 0 4 4) (0 0 2 8) (0 0 0 2)) 88))