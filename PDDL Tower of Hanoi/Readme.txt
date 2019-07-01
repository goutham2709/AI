Goutham Munagala

Command line to invoke the program:
graphplan -o [operators_file] -f [facts_file]

Tower of Hanoi:
Common information in hanoi_facts1 and hanoi_facts2:
(disk1 Object)
(disk2 Object)
(disk3 Object)
(disk4 Object)
(disk5 Object)
(A Object)
(B Object)
(C Object)

7-puzzle:
Common information in both facts_1 and facts_2 are:
(num1 Object)
(num2 Object)
(num3 Object)
(num4 Object)
(num5 Object)
(num6 Object)
(num7 Object)
(TILE11 Object)
(TILE12 Object)
(TILE13 Object)
(TILE21 Object)
(TILE22 Object)
(TILE23 Object)
(TILE31 Object)
(TILE32 Object)
(TILE33 Object)

preconditions:
(adj TILE11 TILE12)
(adj TILE11 TILE21)
(adj TILE12 TILE11)
(adj TILE12 TILE13)
(adj TILE12 TILE22)
(adj TILE13 TILE12)
(adj TILE13 TILE23)
(adj TILE21 TILE11)
(adj TILE21 TILE22)
(adj TILE21 TILE31)
(adj TILE22 TILE12)
(adj TILE22 TILE21)
(adj TILE22 TILE23)
(adj TILE22 TILE32)
(adj TILE23 TILE22)
(adj TILE23 TILE13)
(adj TILE23 TILE33)
(adj TILE31 TILE32)
(adj TILE31 TILE21)
(adj TILE32 TILE31)
(adj TILE32 TILE33)
(adj TILE32 TILE22)
(adj TILE33 TILE32)
(adj TILE33 TILE23)

References:
https://www.tutorialspoint.com/prolog_in_artificial_intelligence/ai_towers_of_hanoi.asp