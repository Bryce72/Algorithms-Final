# Algorithms-Final
Impementing a red black tree.

#### Introduction
Efficient data storage and retrieval are critical in applications where data is frequently accessed and modified. In this project, we explore the application of a Red-Black Tree, a self balancing binary search tree, to store and manage a collection of cooking recipes. The primary objective of this project is to investigate the efficiency of the Red-Black Tree in maintaining sorted order and enabling fast insertion, deletion, and search operations within the context of a recipe database. The question guiding this project is: *How does the Red-Black Tree perform in organizing and retrieving cooking recipes compared to other data structures, and what are its advantages in this specific application?* The rationale for choosing the Red-Black Tree lies in its balanced nature, which guarantees that the height of the tree remains logarithmic with respect to the number of nodes. This property ensures that basic operations, such as insertion, deletion, and search, are performed in O(log n) time, making it a suitable choice for scenarios where quick access to data is essential. By utilizing a Red-Black Tree for storing recipes, we aim to demonstrate how this data structure can be effectively applied to real world problems where ordered data must be managed efficiently. Through this project, we will analyze the performance of the Red-Black Tree in terms of time complexity and compare it with other data structures to highlight its practical benefits and limitations.

## Red-Black Tree code introduction
    - For each tree node, it has int Key and Object value two attributes
    - public void put(int key, Object value), is to insert node to the tree
    -  Node find(int key), is to find node by key value
    - public void remove(int key), is to remove node by key value

We can include the ingredients from the recipe in each node of the tree, allowing us to implement the entire program through adding, deleting, modifying, and querying.