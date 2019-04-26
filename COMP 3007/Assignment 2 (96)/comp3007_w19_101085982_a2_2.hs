import Codec.BMP
import Data.ByteString
import Data.Either
import GHC.Word
import System.IO.Unsafe

{-
Name: Nemanja (Nem) Zutkovic
Student #: 101085982
Execution CMD: search (convertToASCII " .-+*#" True (loadBitmap "sample_image_to_search.bmp"))) (convertToASCII " .-+*#" True (loadBitmap "sample_image_to_find.bmp")))
- The first argument is ASCII art that you will be searching through.
- The second argument is ASCII art that you will be trying to find. 
Output: (x,y) coordinates of the image you are trying to find or (-1,-1) if not found.
-}

-- BEGINNING OF GIVEN SOURCE CODE
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
-- END OF GIVEN SOURCE CODE



-- BEGINNING OF QUESTION 1 CODE
convertRGBValues :: [(Int, Int, Int)] -> [(Int, Int, Int)]
convertRGBValues [] = []
convertRGBValues ((r,g,b):t) = (greyscalevalue, greyscalevalue, greyscalevalue) : (convertRGBValues t)
    where greyscalevalue = lumaConversion r g b

lumaConversion :: Int -> Int -> Int -> Int
lumaConversion r g b = round((x * fromIntegral r) + (y * fromIntegral g) + (z * fromIntegral b))
    where
    x = 0.2126
    y = 0.7152
    z = 0.0722

convertRGBList :: [[(Int, Int, Int)]] -> [[(Int, Int, Int)]]
convertRGBList [] = []
convertRGBList (h:t) = (convertRGBValues h) : (convertRGBList t)

convertRGBToChar :: [Char] -> [(Int, Int, Int)] -> [Char]
convertRGBToChar palette [] = []
convertRGBToChar palette ((r,g,b):t) = (palettesymbol : (convertRGBToChar palette t))
    where palettesymbol = palette !! (round ((fromIntegral ((listlength palette) - 1) / 255) * fromIntegral r))

convertRGBListToCharList :: [Char] -> [[(Int, Int, Int)]] -> [[Char]]
convertRGBListToCharList palette [] = []
convertRGBListToCharList palette (h:t) = (convertRGBToChar palette h) : (convertRGBListToCharList palette t)

convertToASCII :: [Char] -> Bool -> [[(Int, Int, Int)]] -> [[Char]]
convertToASCII palette boolean triplelist
    | listlength palette < 1 = error "Palette is empty. Please provide a set of symbols."
    | boolean == True = (convertRGBListToCharList palette (convertRGBList triplelist))
    | otherwise = (convertRGBListToCharList (reversePalette palette) (convertRGBList triplelist))

reversePalette :: [Char] -> [Char]
reversePalette [] = []
reversePalette (h:t) = (reversePalette t) ++ [h]

listlength :: [anything] -> Int
listlength [] = 0
listlength (h:t) = (listlength t) + 1
-- END OF QUESTION 1 CODE



-- checks if a string is contained within another string
substring :: [Char] -> [Char] -> Bool
substring tosearch [] = False
substring [] (first:rest) = False
substring (h:t) tofind
    | prefix (h:t) tofind = True
    | substring t tofind = True
    | otherwise = False

-- helper function for substring: checks if the second string is a prefix of the first string
prefix :: [Char] -> [Char] -> Bool
prefix tosearch [] = True
prefix [] tofind = False
prefix (char:restofchars) (h:t) = (char == h) && prefix restofchars t

-- finds the y coordinate of the image you are looking for
findY :: [[Char]] -> [Char] -> Int
findY [] tofind = 0
findY (h:t) (first:rest) = if (substring h (first:rest)) == False then findY t (first:rest) + 1 else 0

-- finds the x coordinate of the image you are looking for
findX :: [[Char]] -> [Char] -> Int 
findX asciiart tofind = (findXhelper yline tofind)
   where yline = asciiart !! (findY asciiart tofind)

-- helper function for findX: uses the y coordinate and finds the index of where the image began
findXhelper :: [Char] -> [Char] -> Int
findXhelper [] [] = 0
findXhelper tosearch [] = 0
findXhelper (h:t) tofind = if (prefix (h:t) tofind) == False then (findXhelper t tofind) + 1 else 0

-- main driver function for Q2
search :: [[Char]] -> [[Char]] -> (Int, Int)
search (h:t) (first:rest)
    | (h:t) == (first:rest) = (0,0)
    | listlength (first:rest) < 1 = error "No string to image to search for. Please provide an image."
    | listlength first > listlength h || listlength (first:rest) > listlength (h:t) = (-1,-1)
    | fakePositiveCheck (h:t) (first:rest) /= listlength (first:rest) = (-1,-1)
search asciitosearch (f:b) = (findX asciitosearch f, findY asciitosearch f)

-- helper function for search: checks for false positives
fakePositiveCheck :: [[Char]] -> [[Char]] -> Int
fakePositiveCheck tosearch [] = 0
fakePositiveCheck [] tofind = 0
fakePositiveCheck (h:t) (first:rest) = if (substring h first) then (fakePositiveCheck t rest) + 1 else fakePositiveCheck t (first:rest)