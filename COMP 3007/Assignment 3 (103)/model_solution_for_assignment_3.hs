import Debug.Trace

-- type definition provided to the students

type Node = Int
type Edge = (Node, Node)
data Graph = Single Node | Union Graph Graph [Edge] deriving Show


-- sample graph creation to facilitate debugging

sample_graph = Union (Single 1) (Union (Single 2) (Union (Single 3) (Single 4) []) [(3,2),(4,2)]) [(1,3),(1,4),(2,1)]
sample_adjacency_list = [(1, [3, 4]), (2, [1]), (3, [2]), (4, [2])]
sample_adjacency_matrix = [[False, False, True, True], [True, False, False, False], [False, True, False, False], [False, True, False, False]]


-- general utility

contains :: [Int] -> Int -> Bool
contains [] _ = False
contains (h:t) x
  | x == h = True
  | otherwise = (contains t x)

len :: [a] -> Int
len [] = 0
len (h:t) = 1 + (len t)

rev ::  [Int] -> [Int]
rev [] = []
rev (h:t) = (rev t) ++ [h]

insertOp :: Int -> [Int] -> [Int]
insertOp x [] = [x]
insertOp x (h:t)
  | x <= h = x:(h:t)
  | otherwise = h:(insertOp x t)
  
insertSort :: [Int] -> [Int]
insertSort [] = []
insertSort (h:t) = insertOp h (insertSort t)

-- creating a new graph from an adjacency list

createGraphFromAdjacencyList :: [(Node, [Node])] -> Graph
createGraphFromAdjacencyList arg = createGraphFromNodeListAndEdgeList (getNodesFromAdjacencyList arg) (getEdgesFromAdjacencyList arg)

getNodesFromAdjacencyList :: [(Node, [Node])] -> [Node]
getNodesFromAdjacencyList [] = []
getNodesFromAdjacencyList ( (a, _) : d ) = a : getNodesFromAdjacencyList ( d )

getEdgesFromAdjacencyList :: [(Node, [Node])] -> [Edge]
getEdgesFromAdjacencyList [] = []
getEdgesFromAdjacencyList ( (a, []) : d ) = getEdgesFromAdjacencyList ( d )
getEdgesFromAdjacencyList ( (a, (b:c)) : d ) = (a, b) : getEdgesFromAdjacencyList ( (a, c) : d )

createGraphFromNodeListAndEdgeList :: [Node] -> [Edge] -> Graph
createGraphFromNodeListAndEdgeList [a] _ = Single a
createGraphFromNodeListAndEdgeList (a : b) c = Union (Single a) (createGraphFromNodeListAndEdgeList b c) [ (d, e) | (d, e) <- c, (d == a && (contains b e)) || (e == a && (contains b d))]


-- creating a new graph from an adjacency matrix

checkIfMatrixIsSquare :: [[Bool]] -> Bool
checkIfMatrixIsSquare a = checkIfMatrixIsSquare' a (len a)

checkIfMatrixIsSquare' :: [[Bool]] -> Int -> Bool
checkIfMatrixIsSquare' [] _ = True
checkIfMatrixIsSquare' (h:t) a
  | len h == a = (checkIfMatrixIsSquare' t a)
  | otherwise = False

createGraphFromAdjacencyMatrix :: [[Bool]] -> Graph
createGraphFromAdjacencyMatrix arg = createGraphFromNodeListAndEdgeList (getNodesFromAdjacencyMatrix arg) (getEdgesFromAdjacencyMatrix arg)
  
getNodesFromAdjacencyMatrix :: [[Bool]] -> [Node]
getNodesFromAdjacencyMatrix a = getNodesFromAdjacencyMatrix' a 1
  
getNodesFromAdjacencyMatrix' :: [[Bool]] -> Int -> [Node]
getNodesFromAdjacencyMatrix' [] _ = []
getNodesFromAdjacencyMatrix' (a:b) c = c : getNodesFromAdjacencyMatrix' b (c + 1)

getEdgesFromAdjacencyMatrix :: [[Bool]] -> [Edge]
getEdgesFromAdjacencyMatrix a = getEdgesFromAdjacencyMatrix' a 1 1

getEdgesFromAdjacencyMatrix' ::  [[Bool]] -> Int -> Int -> [Edge]
getEdgesFromAdjacencyMatrix' [] _ _ = []
getEdgesFromAdjacencyMatrix' ([] : c) row col = getEdgesFromAdjacencyMatrix' c (row + 1) 1
getEdgesFromAdjacencyMatrix' ((a : b) : c) row col
  | a == True = (row, col) : ( getEdgesFromAdjacencyMatrix' (b : c) row (col + 1) )
  | otherwise = ( getEdgesFromAdjacencyMatrix' (b : c) row (col + 1) )
  
  
-- checking to see if a node is in a graph

isNodeInGraph :: Node -> Graph -> Bool
isNodeInGraph a (Single b)
  | a == b = True
  | otherwise = False
isNodeInGraph a (Union b c _) = (isNodeInGraph a b) || (isNodeInGraph a c)


-- checking to see if an edge is in a graph

isEdgeInGraph :: Edge -> Graph -> Bool
isEdgeInGraph _ (Single _) = False
isEdgeInGraph a (Union b c []) = (isEdgeInGraph a b) || (isEdgeInGraph a c)
isEdgeInGraph a (Union b c (d : e))
  | a == d = True
  | otherwise = isEdgeInGraph a (Union b c e)

  
-- getting all the nodes from a graph

getAllNodesFromGraph :: Graph -> [Node]
getAllNodesFromGraph (Single a) = [a]
getAllNodesFromGraph (Union a b _) = (getAllNodesFromGraph a) ++ (getAllNodesFromGraph b)


-- getting all the edges from a graph

getAllEdgesFromGraph :: Graph -> [Edge]
getAllEdgesFromGraph (Single a) = []
getAllEdgesFromGraph (Union a b c) = (getAllEdgesFromGraph a) ++ (getAllEdgesFromGraph b) ++ c


-- check if graph is a 'singleton'

isGraphSingleton :: Graph -> Bool
isGraphSingleton (Single _) = True
isGraphSingleton (Union _ _ _) = False


-- compute a breadth first search traversal from an arbitrary starting point

breadthFirstSearchTraversal :: Graph -> Node -> [Node]
breadthFirstSearchTraversal graph start = rev (breadthFirstSearchTraversal' graph [start] [])

breadthFirstSearchTraversal' :: Graph -> [Node] -> [Node] -> [Node]
breadthFirstSearchTraversal' _ [] traversal = traversal
breadthFirstSearchTraversal' graph (front : rear) traversal = breadthFirstSearchTraversal' graph (rear ++ (insertSort [ node | node <- (getAllAdjacentNodes graph front), (not (contains traversal node) && not (contains rear node)) ])) (front : traversal)

getAllAdjacentNodes :: Graph -> Node -> [Node]
getAllAdjacentNodes (Single _) _ = []
getAllAdjacentNodes (Union a b [] ) c = (getAllAdjacentNodes a c) ++ (getAllAdjacentNodes b c)
getAllAdjacentNodes (Union a b ( (c, d) : e) ) f
  | c == f = d : (getAllAdjacentNodes (Union a b e) f)
  | otherwise = (getAllAdjacentNodes (Union a b e) f)