import Codec.BMP
import Data.ByteString
import Data.Either
import GHC.Word
import System.IO.Unsafe

{-
Name: Nemanja (Nem) Zutkovic
Student #: 101085982
Execution CMD: showAsASCIIArt (convertToASCII " .-+*#" True (loadBitmap "sample_image_to_search.bmp")))
- The first argument only accepts list of n characters.
- The second argument only accepts True or False.
- The third argument only accepts (loadBitmap "<bmp-file>").
Output: The bmp file as ASCII art.
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



-- returns a new set of RGB values based on the current RGB values (greyscale RGB values)
convertRGBValues :: [(Int, Int, Int)] -> [(Int, Int, Int)]
convertRGBValues [] = []
convertRGBValues ((r,g,b):t) = (greyscalevalue, greyscalevalue, greyscalevalue) : (convertRGBValues t)
    where greyscalevalue = lumaConversion r g b

-- helper function to return the luma of an RGB value: https://en.wikipedia.org/wiki/Luma_(video)
lumaConversion :: Int -> Int -> Int -> Int
lumaConversion r g b = round((x * fromIntegral r) + (y * fromIntegral g) + (z * fromIntegral b))
    where
    x = 0.2126
    y = 0.7152
    z = 0.0722

-- with the help of convertRGBValues, returns a greyscale version of the incoming 2d list
convertRGBList :: [[(Int, Int, Int)]] -> [[(Int, Int, Int)]]
convertRGBList [] = []
convertRGBList (h:t) = (convertRGBValues h) : (convertRGBList t)

-- "converts" a list of tuple integers to a list of characters
convertRGBToChar :: [Char] -> [(Int, Int, Int)] -> [Char]
convertRGBToChar palette [] = []
convertRGBToChar palette ((r,g,b):t) = (palettesymbol : (convertRGBToChar palette t))
    where palettesymbol = palette !! (round ((fromIntegral ((listlength palette) - 1) / 255) * fromIntegral r))

-- with the help of convertRGBToChar, "converts" a 2d list of tuple integers to a 2d list of characters
convertRGBListToCharList :: [Char] -> [[(Int, Int, Int)]] -> [[Char]]
convertRGBListToCharList palette [] = []
convertRGBListToCharList palette (h:t) = (convertRGBToChar palette h) : (convertRGBListToCharList palette t)

-- main driver function
convertToASCII :: [Char] -> Bool -> [[(Int, Int, Int)]] -> [[Char]]
convertToASCII palette boolean triplelist
    | listlength palette < 1 = error "Palette is empty. Please provide a set of symbols."
    | boolean == True = (convertRGBListToCharList palette (convertRGBList triplelist))
    | otherwise = (convertRGBListToCharList (reversePalette palette) (convertRGBList triplelist))

-- helper function for convertToASCII: reverses a list of characters
reversePalette :: [Char] -> [Char]
reversePalette [] = []
reversePalette (h:t) = (reversePalette t) ++ [h]

-- helper function for convertToASCII/convertRGBToChar: returns the length of any list
listlength :: [anything] -> Int
listlength [] = 0
listlength (h:t) = (listlength t) + 1