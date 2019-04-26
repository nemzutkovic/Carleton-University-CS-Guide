-- Name: Nemanja (Nem) Zutkovic
-- Student #: 101085982

type Node = Int
type Edge = (Node, Node)
data Graph = Single Node | Union Graph Graph [Edge] deriving Show

-- Question 1: "Does this Node Appear in this Graph?"
checkForNode :: Node -> Graph -> Bool
checkForNode node (Single x) = if node == x then True else False
checkForNode node (Union graphA graphB edgelist)
  | checkForNode node graphA == True = True
  | checkForNode node graphB == True = True
  | otherwise = False

-- Question 2: "Does this Edge Appear in this Graph?"
checkForEdge :: Edge -> Graph -> Bool
checkForEdge edge (Single node) = False
checkForEdge edge (Union graphA graphB edgelist)
  | (checkForEdgeHelper edge edgelist) == True = True
  | checkForEdge edge graphA == True = True
  | checkForEdge edge graphB == True = True
  | otherwise = False

checkForEdgeHelper :: Edge -> [Edge] -> Bool
checkForEdgeHelper edge [] = False
checkForEdgeHelper edge ((a,b):t) = if edge == (a,b) then True else checkForEdgeHelper edge t

-- QUESTION 3: "List All Nodes That Appear in this Graph"
listofNodes :: Graph -> [Node]
listofNodes (Single node) = [node]
listofNodes (Union graphA graphB edges) = listofNodes graphA ++ listofNodes graphB

-- QUESTION 4: "List All Edges That Appear in this Graph"
listofEdges :: Graph -> [Edge]
listofEdges (Single node) = []
listofEdges (Union graphA graphB edges) = edges ++ listofEdges graphA ++ listofEdges graphB

-- QUESTION 5: "Is this Graph a Singleton?"
singleton :: Graph -> Bool
singleton (Single node) = True
singleton (Union graphA graphB edges) = False

-- QUESTION 6: "Compute a Breadth-First Search Traversal"
bfs :: Graph -> Node  -> [Node]
bfs (Single graph) node = if graph == node then [node] else []
bfs graph node = bfsHelper graph node 1 (listofNodes graph) [node]

bfsHelper :: Graph -> Node -> Int -> [Node] -> [Node] -> [Node]
bfsHelper (Single graph) _ _ _ _ = []
bfsHelper graph node num allnodes nodesvisited 
  | (lengthlist nodesvisited == lengthlist allnodes) || (num > lengthlist nodesvisited) = nodesvisited
  | nodeExists allnodes node == False = []
  | otherwise = bfsHelper graph (checkNextNode (f:rest) num 0) (num + 1) allnodes (f:rest)
  where edges = sortEdges (extractEdges (listofEdges graph) node)
        (f:rest) = nodesvisited ++ (addNewNodes nodesvisited edges)

checkNextNode :: [Node] -> Int -> Int -> Node
checkNextNode (h:t) num temp
  | num == temp = h
  | otherwise = checkNextNode t num (temp + 1)

addNewNodes :: [Node] -> [Edge] -> [Node]
addNewNodes [] _ = []
addNewNodes _ [] = []
addNewNodes nodesvisited ((a,b):t)
  | nodeExists nodesvisited b == True = addNewNodes nodesvisited t
  | otherwise = b : addNewNodes nodesvisited t 

nodeExists :: [Node] -> Node -> Bool
nodeExists [] _ = False
nodeExists (h:t) node = if h == node then True else nodeExists t node

extractEdges :: [Edge] -> Node -> [Edge]
extractEdges [] node = []
extractEdges ((a,b):t) node
  | node == a = (a,b) : extractEdges t node
  | otherwise = extractEdges t node

sortEdges :: [Edge] -> [Edge]
sortEdges [] = []
sortEdges ((a,b):t) = (sortEdges [(c,d) | (c,d)<-t, (c,d)<=(a,b)]) ++ [(a,b)] ++ (sortEdges [(c,d) | (c,d)<-t, (c,d)>(a,b)])

lengthlist :: [anything] -> Int
lengthlist [] = 0
lengthlist (h:t) = 1 + lengthlist t

-- Question 7: "Create a Graph from an Adjacency List"
adjListToGraph :: [(Node, [Node])] -> Graph
adjListToGraph [] = error "This is not a valid Adjacency List."
adjListToGraph ((n1,_):[]) = Single n1
adjListToGraph ((n1,e1):(n2,e2):[]) = Union (Single n1) (Single n2) ((genEdges (n1,e1)) ++ (genEdges (n2,e2)))
adjListToGraph ((n1,e1):t) = Union (Single n1) (adjListToGraph t) (genEdges (n1,e1))

genEdges :: (Node, [Node]) -> [Edge]
genEdges (node,[]) = []
genEdges (node,(h:t)) = (node,h) : genEdges (node,t)

-- Question 8: "Create a Graph from an Adjacency Matrix"
adjMatrixToGraph :: [[Bool]] -> Graph
adjMatrixToGraph [] = error "This is an empty Adjacency Matrix."
adjMatrixToGraph [[]] = error "This is an empty Adjacency Matrix."
adjMatrixToGraph booleans
  | squareCheck booleans booleans == False = error "This is not a square Adjacency Matrix."
  | otherwise = adjListToGraph (matrixCounter booleans 1)

squareCheck :: [[Bool]] -> [[Bool]] -> Bool
squareCheck booleans [] = True
squareCheck booleans (h:t)
  | lengthlist booleans == lengthlist h = squareCheck booleans t
  | otherwise = False

matrixCounter :: [[Bool]] -> Node -> [(Node,[Node])]
matrixCounter [] _ = []
matrixCounter ((h:t):rest) node = (node, matrixCounterHelper (h:t) 1) : matrixCounter rest (node + 1)

matrixCounterHelper :: [Bool] -> Node -> [Node]
matrixCounterHelper [] _ = []
matrixCounterHelper (h:t) node
  | h == True = node : matrixCounterHelper t (node + 1)
  | otherwise = matrixCounterHelper t (node + 1)