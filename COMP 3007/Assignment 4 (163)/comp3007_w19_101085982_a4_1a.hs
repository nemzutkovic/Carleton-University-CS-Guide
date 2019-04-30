countZerofree :: [Int] -> Int
countZerofree list = countZeroFreeOptimized list 0

countZeroFreeOptimized :: [Int] -> Int -> Int
countZeroFreeOptimized [] accum = accum
countZeroFreeOptimized (h:t) accum
  | h < 0 = countZeroFreeOptimized (h * (-1):t) accum
  | h > 0 && h < 10 = countZeroFreeOptimized t accum + 1
  | moduloOptimized 10 h 0 == 0 = countZeroFreeOptimized t accum
  | otherwise = countZeroFreeOptimized (h `div` 10:t) accum

moduloOptimized :: Int -> Int -> Int -> Int
moduloOptimized x y accum
  | accum < y = moduloOptimized x y (accum + x)
  | otherwise = accum - y