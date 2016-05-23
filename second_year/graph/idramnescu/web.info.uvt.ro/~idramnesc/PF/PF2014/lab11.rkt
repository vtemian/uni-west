#lang racket
;; Exercitiul 1 - intarzierea evaluarii folosind inchideri functionale
(define a (cons (λ () b) (λ () a)))
(define b (cons (λ () a) (λ () b)))
;(car a)
;(cdr a)
;(car b)
;(cdr b)
;((cdr a))
;(equal? a ((cdr a)))
;(equal? a ((car b)))

;; Discutie:
;; - fara inchideri functionale, am fi obtinut o eroare
;; - in momentul definirii lui a nu stiam cine este b si nici cine este a
;; - multumita inchiderilor functionale, nici nu trebuie sa stim aceste
;;   lucruri la momentul definirii; a devine o pereche dintre (λ () b) si
;;   (λ () a) cu contextul: b<-? (variabila inca nelegata la nimic) 
;;   si a<-? (la fel)
;; - in momentul invocarii ((cdr a)) se apeleaza inchiderea pe zero argumente
;;   obtinandu-se a, care acum este legat la o pereche de inchideri functionale

;; Exercitiul 2 - constructori de baza si operatori pt fluxuri
;; constructori de baza:
(define empty-stream '())
(define-syntax cell
  (syntax-rules ()
    ((_ term rest)
     (cons term (lambda () rest)))))

;; operatori:
;; observati ((cdr s))
;; (cdr s) produce o inchidere functionala 
;; ca sa avem acces la fluxul din ea e nevoie sa o aplicam pe zero argumente
;; => ((cdr s))
(define head car)
(define tail (lambda (s) ((cdr s))))
(define empty-stream? null?)

;(head (cell 0 (cell 1 empty-stream)))
;(head (tail (cell 0 (cell 1 empty-stream))))

;; OBSERVATIE
;; - dupa implementarea constructorilor de baza si a operatorilor
;;   uitati felul in care acestia au fost implementati!
;; - de acum incolo veti realiza lucrul cu fluxuri numai prin intermediul
;;   lui cell, head, tail etc, nu umbland voi insiva cu inchideri functionale!
;; - in acest fel, daca schimbam implementarea pt cell, head, tail etc, 
;;   programele scrise in laborator vor functiona in continuare perfect
;; - aceasta separare intre implementarea constructorilor si operatorilor pe
;;   fluxuri si utilizarea lor poarta numele f important de ABSTRACTIZARE
;; - practic lucrul cu fluxuri va fi identic cu lucrul cu liste unde
;;   cons devine cell, car devine head, cdr devine tail etc

;; Exercitiul 3 - fluxul numerelor naturale
(define naturals-stream 
  (let naturals ((seed 0))
    (cell seed (naturals (add1 seed)))))

;(head naturals-stream)
;(head (tail naturals-stream))
;(head (tail (tail naturals-stream)))

;; Operatorii de lazy evaluation: delay & force
;;---------------------------------------------

;; delay: expresie -> promisiune
;; (delay expr) creeaza o "promisiune" din expr, pe care inca nu o evalueaza. 
;; Evaluarea se poate forta ulterior (cand e nevoie) prin aplicarea 
;; operatorului force asupra "promisiunii".

;; (delay expr) este ca o cutiuta care retine tripletul: 
;; (  expr 
;;    un flag care spune daca expr s-a evaluat sau nu
;;    un cache in care se pastreaza valoarea expr, odata evaluata  )

;; A doua aplicare a operatorului force asupra expresiei "intarziate" 
;; nu produce o reevaluare, ci valoarea va fi luata din cache.

;; force: promisiune -> valoare
;; (force promisiune) se uita la flag si:
;; - daca e true, intoarce rezultatul din cache
;; - altfel:
;;   - evalueaza expresia
;;   - pune rezultatul in cache
;;   - seteaza flagul pe true
;;   - intoarce rezultatul evaluarii

;; Utilizari:
;; - imbunatatirea performantei (evitarea de calcule inutile)
;; - lucrul cu fluxuri infinite
;; - generatoare de solutii pt probleme care isi reprezinta 
;;   spatiul starilor ca graf

;; Exercitiul 4
;; A doua aplicare a unui force nu produce o reevaluare, 
;; ci intoarcerea rezultatul deja stocat in cache.
;; Nu era asa in cazul inchiderilor functionale.
(display "4a.  Pt inchideri functionale:\n")
(define a 2)
(define p (lambda () (+ a 5)))
(p)
(define a 6)
(p)

(display "4b.  Pt delay si force:\n")
(define a 2)
(define p (delay (+ a 5)))
(force p)
(define a 6)
(force p)

;; Exercitiul 5 - generarea unui flux de palindroame pe un alfabet dat
;; mai intai o lista cu palindroamele de lungime 1, 
;; apoi una cu cele de lungime 2, 3 etc
;; (take 3 (palindromes '(a b c))) => 
;; (((a) (b) (c)) 
;;  ((a a) (b b) (c c)) 
;;  ((a a a) (b a b) (c a c) (a b a) (b b b) (c b c) (a c a) (b c b) (c c c)))
(define empty-stream '())
(define-syntax cell
  (syntax-rules ()
    ((_ term rest)
     (cons term (delay rest)))))

(define head car)
(define tail (lambda (s) (force (cdr s))))
(define empty-stream? null?)

;; observati ca take-stream si drop-stream trebuie sa functioneze indiferent
;; daca am implementat fluxul cu inchideri functionale sau cu delay si force!
(define take-stream
  (lambda (n s)
    (if (or (zero? n) (empty-stream? s))
        '()  ;; lista vida pt ca take trebuie sa intoarca o LISTA
        (cons (head s) (take-stream (- n 1) (tail s))))))

(define drop-stream
  (lambda (n s) 
    (if (or (empty-stream? s) (zero? n))
        s  ;; fluxul vid pt ca drop trebuie sa intoarca un FLUX
        (drop-stream (- n 1) (tail s)))))

(define (expand-pal alphabet) 
  ;; palindroame noi se obtin adaugand un acelasi simbol 
  ;; la inceputul si sfarsitul unui palindrom
  (λ (L)
    (if (null? alphabet) 
        '()
        (cons (append (list (car alphabet)) ;; la inceput
                      L 
                      (list (car alphabet))) ;; la sfarsit
              ((expand-pal (cdr alphabet)) L))))) ;; acelasi lucru cu restul de simboluri

(define (pal-stream alphabet)
  (let iter ((term1 (map list alphabet))
             (term2 (map (λ (x) (list x x)) alphabet)))
    (cell term1
          (iter term2 (apply append (map (expand-pal alphabet) term1))))))

(display "5.  (take 3 (pal-stream '(a b c)))\n")
(take-stream 3 (pal-stream '(a b c)))


