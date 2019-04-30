countZeroFree :: [Int] -> Int -- the "helper" function
countZeroFree x = countZeroFree' x 0

countZeroFree' :: [Int] -> Int -> Int -- the tail-call optimized function
countZeroFree' [] a = a
countZeroFree' (h:t) a
  | containsZero h = (countZeroFree' t (a+1))
  | otherwise = (countZeroFree' t a)  
  
-- a version that is NOT tail-call optimized, included here only for reference
-- countZeroFree :: [Int] -> Int
-- countZeroFree [] = 0
-- countZeroFree (h:t)
  -- | containsZero h = 1 + (countZeroFree t)
  -- | otherwise = (countZeroFree t)
  
containsZero :: Int -> Bool
containsZero x
  | x == 0 = True
  | x < 10 = False
  | otherwise = ((myMod x 10) == 0) || (containsZero (myDiv x 10))

myDiv :: Int -> Int -> Int
myDiv x y = myDiv' x y 0

myDiv' :: Int -> Int -> Int -> Int
myDiv' x y q
  | x < y = q
  | otherwise = (myDiv' (x-y) y (q+1))

myMod :: Int -> Int -> Int
myMod x y
  | x < y = x
  | otherwise = (myMod (x - y) y)

