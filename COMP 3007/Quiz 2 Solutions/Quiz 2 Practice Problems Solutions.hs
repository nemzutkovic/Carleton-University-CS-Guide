-- QUESTION 1
data ListOfInts = EmptyList | Single Int | Cons ListOfInts Int Int

avrage :: ListOfInts -> Float
avrage EmptyList = 0
avrage (Single num) = fromIntegral num
avrage listofints = fromIntegral (sum' listofints) / fromIntegral (length' listofints)

sum' :: ListOfInts -> Int
sum' EmptyList = 0
sum' (Single num) = num
sum' (Cons list x y) = x + y + (sum' list)

length' :: ListOfInts -> Int
length' EmptyList = 0
length' (Single num) = 1
length' (Cons list x y) = 2 + (length' list)

-- QUESTION 2
directors_db :: [ ( [Char] , [Char] ) ]
directors_db = [ ("Carpenter", "Assault on Precinct 13"), ("Carpenter", "The Thing"), ("Carpenter", "Big Trouble in Little China"), ("Tarantino", "Inglourious Basterds"), ("Tarantino", "Django Unchained"), ("Tarantino", "The Hateful Eight") ]

year_db :: [ ( [Char] , Int ) ]
year_db = [ ("Escape from New York", 1981), ("The Thing", 1982), ("Big Trouble in Little China", 1986), ("Pulp Fiction", 1994), ("Django Unchained", 2012), ("The Hateful Eight", 2015) ]

-- QUESTION 2A
movieTitles :: [ ( [Char] , [Char] ) ] -> [Char] -> [[Char]]
movieTitles [] _ = []
movieTitles _ [] = []
movieTitles ((name,movie):t) director
  | name == director = movie : movieTitles t director
  | otherwise = movieTitles t director

-- QUESTION 2B
movieYears :: [ ( [Char] , Int ) ] -> Int -> [[Char]]
movieYears [] _ = []
movieYears ((movie,releaseyear):t) decade
  | (findDecade releaseyear) == decade = movie : movieYears t decade
  | otherwise = movieYears t decade

findDecade :: Int -> Int
findDecade releaseyear
  | releaseyear >= 1980 && releaseyear <= 1989 = 80
  | releaseyear >= 1990 && releaseyear <= 1999 = 90
  | releaseyear >= 2000 && releaseyear <= 2009 = 0
  | otherwise = 10

-- QUESTION 2C
findDirector :: Int -> [ ( [Char] , Int ) ] -> [ ( [Char] , [Char] ) ] -> Maybe [Char]
findDirector _ [] _ = Nothing
findDirector _ _ [] = Nothing
findDirector year ((movie,releaseyear):t) directorlist
  | year == releaseyear && found /= [] = Just found
  | otherwise = findDirector year t directorlist
  where found = searchForDirector movie directorlist

searchForDirector :: [Char] -> [ ( [Char] , [Char] ) ] -> [Char]
searchForDirector movietofind [] = []
searchForDirector movietofind ((name,movie):t)
  | movietofind == movie = name
  | otherwise = searchForDirector movietofind t

-- QUESTION 3 (Structural Induction)

-- QUESTION 4
foo' :: [Int] -> [Int]
foo' [] = []
foo' list = fooOptimized list []

fooOptimized :: [Int] -> [Int] ->[Int]
fooOptimized [] _ = []
fooOptimized (h:t) nlist = nlist ++ [alter h] ++ (fooOptimized t nlist)

alter :: Int -> Int
alter num = num + 1

-- QUESTION 5
sumOfEvens :: [Int] -> Int
sumOfEvens [] = 0
sumOfEvens (h:t) = if even h then h + sumOfEvens t else sumOfEvens t

-- QUESTION 6
sumOfEvens' :: [Int] -> Int
sumOfEvens' [] = 0
sumOfEvens' list = foldr (+) 0 (filter even list)

-- QUESTION 7 (Prolog)
-- grandaunt(A,B) :- parent(X,A), parent(Y,X), sibling(Y,B).

-- QUESTION 8 (Prolog)
-- X = 1,
-- X = 2,
-- X = 3,
-- X = 1,
-- X = 1,
-- X = 2,
-- X = 3.