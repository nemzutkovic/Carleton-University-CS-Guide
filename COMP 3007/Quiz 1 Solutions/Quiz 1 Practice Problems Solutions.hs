-- QUESTION 1
range :: Int -> Int -> Int -> [Int]
range start stop step
  | start < stop = start : (range (start+step) stop step)
  | otherwise = []

-- QUESTION 2A
selectThird :: [anything] -> anything
selectThird (a:(b:(c:t))) = c

everyThird :: [Char] -> [Char]
everyThird (a:(b:(c:t))) = selectThird (a:(b:(c:t))) : everyThird t
everyThird _ = []

-- QUESTION 2B
everyThirdElem :: [Char] -> [Char]
everyThirdElem (a:(b:(c:t))) = c : (everyThirdElem t)
everyThirdElem _ = []

-- QUESTION 3
listcomprehension = [(a,b) | a <- [0..5], b <- [0..5], a < b && a + b == 6]

-- QUESTION 4
orderedPairs' :: [(Int, Int)] -> [(Int, Int)]
orderedPairs' [] = []
orderedPairs' ((a,b):t)
  | (a + b) == 6 && (a < b) = (a,b) : (orderedPairs' t)
  | otherwise = orderedPairs' t

-- QUESTION 5
findSideDim :: [(Int, Int, Int)] -> Int -> Int
findSideDim list x
  | x * x == (length list) = x
  | otherwise = findSideDim list (x + 1)

repackWorker :: [(Int, Int, Int)] -> Int -> [[(Int, Int, Int)]]
repackWorker [] num = []
repackWorker list num = (take num list) : (repackWorker (drop num list) num)

repack :: [(Int, Int, Int)] -> [[(Int, Int, Int)]]
repack [] = []
repack list = repackWorker list (findSideDim list 0)