import Codec.BMP
import Data.ByteString
import Data.Either
import GHC.Word
import System.IO.Unsafe

import Debug.Trace


-- These functions were provided with the specification

loadBitmap :: FilePath -> [[(Int, Int, Int)]]
loadBitmap filename = repackAs2DList (either returnEmptyOnError processDataOnBMP (unsafePerformIO (readBMP filename)))
  
returnEmptyOnError :: Error -> ([(Int, Int, Int)], (Int, Int))
returnEmptyOnError _ = ([], (0, 0))

processDataOnBMP :: BMP -> ([(Int, Int, Int)], (Int, Int))
processDataOnBMP bmp = ((parseIntoRGBVals (convertToInts (unpack (unpackBMPToRGBA32 bmp)))), (bmpDimensions bmp))
  
convertToInts :: [Word8] -> [Int]
convertToInts [] = []
convertToInts (h:t) = (fromIntegral (toInteger h)) : (convertToInts t)

parseIntoRGBVals :: [Int] -> [(Int, Int, Int)]
parseIntoRGBVals [] = []
parseIntoRGBVals (h:i:j:_:t) = (h,i,j) : (parseIntoRGBVals t)

repackAs2DList :: ([(Int, Int, Int)], (Int, Int)) -> [[(Int, Int, Int)]]
repackAs2DList (pixels, (width, height)) = (Prelude.reverse (repackAs2DList' pixels width height))

repackAs2DList' :: [(Int, Int, Int)] -> Int -> Int -> [[(Int, Int, Int)]]
repackAs2DList' []  width  height = []
repackAs2DList' pixels width height = (Prelude.take width pixels) : (repackAs2DList' (Prelude.drop width pixels) width height)

showAsASCIIArt :: [[Char]] -> IO ()
showAsASCIIArt pixels = Prelude.putStr (unlines pixels)


-- These functions address the requirements of Question 1
--
--   n.b., the syntax for showing the ASCII art produced is << showAsASCIIArt (convertToASCIIArt ".-+*#" True (loadBitmap "sample_image_to_search.bmp"))) >>

-- this is a helper function that first reverses the palette if the Boolean second argument is False
convertToASCIIArt :: [Char] -> Bool -> [[(Int, Int, Int)]] -> [[Char]]
convertToASCIIArt palette True image = rgbToIndexedColour palette image
convertToASCIIArt palette False image = rgbToIndexedColour (reversePalette palette) image

-- these functions are the fairly straightforward "traverse a 2d-list with two recursive functions"
rgbToIndexedColour :: [Char] -> [[(Int, Int, Int)]] -> [[Char]]
rgbToIndexedColour _ [] = []
rgbToIndexedColour palette (h:t) = (rgbToIndexedColour' palette h) : (rgbToIndexedColour palette t)

rgbToIndexedColour' :: [Char] -> [(Int, Int, Int)] -> [Char]
rgbToIndexedColour' _ [] = []
rgbToIndexedColour' palette (h : t) = palette !! (round ((computeLuminosity h) * ((getSizeOfPalette palette)-1))) : (rgbToIndexedColour' palette t)

-- luminosity is superior to averaging the r, g, and b components
computeLuminosity :: (Int, Int, Int) -> Float
computeLuminosity (r, g, b) = ((0.21 * (convertIntToFloat r)) + (0.72 * (convertIntToFloat g)) + (0.07 * (convertIntToFloat b))) / 255

convertIntToFloat :: Int -> Float
convertIntToFloat n = fromIntegral n :: Float

-- these functions are built-in to Prelude, but built-in functions (except round and fromIntegral) were not permitted
getSizeOfPalette :: [Char] -> Float
getSizeOfPalette [] = 0
getSizeOfPalette (h:t) = 1 + (getSizeOfPalette t)

concatenateLists :: [Char] -> [Char] -> [Char]
concatenateLists [] x = x
concatenateLists (h:t) x = h : (concatenateLists t x)

reversePalette ::  [Char] -> [Char]
reversePalette [] = []
reversePalette (h:t) = concatenateLists (reversePalette t) [h]


-- These functions address the requirements of Question 2
--
--   n.b., the syntax for conducting the search is << findWithinImage (convertToASCIIArt " .:-=+*#%@" True (loadBitmap "sample_image_to_search.bmp")) (convertToASCIIArt " .:-=+*#%@" True (loadBitmap "sample_image_to_find.bmp")) >>


-- this is a helper function, returning 0, 0 if the image to find is empty (and thus, everywhere) or setting the initial row argument to 0 
findWithinImage :: [[Char]] -> [[Char]] -> (Int, Int)
findWithinImage _ [] = (0, 0)
findWithinImage imageToSearch imageToFind = findMatchingRow imageToSearch imageToFind 0

-- this function checks each row, and if that row doesn't represent the first row of a complete match, it increases the row argument and makes a recursive call
-- the function can tell if it was a complete match by finding at what column (i.e., y co-ordinate) the first row of the image to find appears, and then checking
-- to see if every subsequent row matches the image to find if the same number of elements were dropped from each subsequent row of the image to search
findMatchingRow :: [[Char]] -> [[Char]] -> Int -> (Int, Int)
findMatchingRow [] _ _ = (-1, -1)
findMatchingRow (h:t) (h':t') row
  | ((findMatchingColumn h h') > -1) && (checkRemainingRows t (findMatchingColumn h h') t') = ((findMatchingColumn h h'), row)
  | otherwise = findMatchingRow t (h':t') (row + 1)

findMatchingColumn :: [Char] -> [Char] -> Int
findMatchingColumn [] _ = -1
findMatchingColumn within for
  | ((Prelude.take (computeLength for) within) == for) = 0
  | otherwise = 1 + (findMatchingColumn (Prelude.drop 1 within) for)

checkRemainingRows :: [[Char]] -> Int -> [[Char]] -> Bool
checkRemainingRows [] _ _ = False
checkRemainingRows _ _ [] = True
checkRemainingRows (h:t) n (h':t')
  | (confirmMatch (Prelude.drop n h) h') = checkRemainingRows t n t'
  | otherwise = False  

confirmMatch _ [] = True
confirmMatch [] _ = False
confirmMatch (h:t) (h':t')
  | (h == h') = (confirmMatch t t')
  | otherwise = False
  
-- these functions are built-in to Prelude, but built-in functions (except round and fromIntegral) were not permitted
takeElements :: Int -> [a] -> [a]
takeElements 0 _ = []
takeElements n (h:t) = h : (takeElements (n-1) t)

dropElements :: Int -> [a] -> [a]
dropElements 0 list = list
dropElements n (h:t) = (dropElements (n-1) t)

computeLength :: [Char] -> Int
computeLength [] = 0
computeLength (h:t) = 1 + (computeLength t)
