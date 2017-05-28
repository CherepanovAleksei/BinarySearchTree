# BinarySearchTree
# Performance analysis
The following tables show approximate amount of time spent on tree operations. All tests were performed using JUnit 5 testing framework from EqualTree.kt -Test. *The time is shown in seconds*.

## Binary search tree

| Elements | Queue Insert | Queue Search | Random Insert | Random Search |
|:--------:|:---------------:|:-----------------:|:----------------:|:-------------:|
| 100      |       0.014        |         0.002         |        0.017        |       0.002       |
| 1000     |       0.028        |         0.12         |         0.014       |       0.008       |
| 10000    |       0.43       |         0.636         |        0.026        |       0.012       |
| 100000   |     -      |         -         |        0.15       |       0.144      |
| 1000000  |        -        |         -         |       1.798       |       1.429      |
| 10000000 |        -        |         -         |       40.868      |      21.705      |


## Red-black tree

| Elements | Queue Insert | Queue Search | Random Insert | Random Search |
|:--------:|:--------------------:|:-----------------:|:----------------:|:-------------:|
| 100      |          0.016         |         0.001         |         0.12        |       0.002       |
| 1000     |          0.026          |         0.004         |        0.016        |       0.01       |
| 10000    |          0.022          |         0.004         |        0.035        |      0.011       |
| 100000   |          0.052          |         0.032         |        0.255       |       0.099      |
| 1000000  |          2.826         |        0.198         |       2.603       |       1.317      |
| 10000000 |         25.362        |        42.691        |       34.735      |      22.714      |


## B-tree

t = 100

| Elements | Queue Insert | Queue Search | Random Insert | Random Search |
|:--------:|:--------------------:|:-----------------:|:----------------:|:-------------:|
| 100      |          0.018          |         0.004         |        0.026        |        0.003      |
| 1000     |          0.015          |         0.016         |        0.012        |       0.028       |
| 10000    |          0.134         |         0.018         |        0.068       |       0.105       |
| 100000   |          0.335         |         0.176        |        0.373       |       0.287      |
| 1000000  |          1.747        |         0.543        |       2.426       |       1.547      |
| 10000000 |          18.943        |        4.692        |       35.151      |       30.305     |