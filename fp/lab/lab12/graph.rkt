(define (dfs G v)
    (letrec ([visited (list v)]
    [dfs-list (list (cons v 'none))]
    [dfs-aux (lambda (x)
    (map
    ; do something for all non-visited neighbours y of x
    (lambda (y)
    (when (not (member y visited))
    ; add y to the list of visited nodes
    ...
    ; add (cons y x) to dfs-list
    ...
    ; continue
    ...))
    (outgoing G x)))])
    (dfs-aux v)
    ; the visited nodes were added to dfs-list in reversed order
    ; ~~> dfs-list must be reversed
(reverse dfs-list)))

(set! visited (cons y visited)) ;add an element to a list
(set! dfs-list (cons x y))
