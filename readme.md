

# Advent of Code

My implementations of the Advent of Code puzzles.

My answers are calculated using Github Actions. Microsoft robots kneel before my
code and execute their orders. After the robots finish their duties&#x2014;like the
good robots that they are&#x2014;they write a report in a human-friendly format,
Markdown&#x2014;the lingua franca of the Github Kingdom. After all, it is US, humans,
who want to understand what the hell happened in the field.

In particular, their orders are:

1.  Pick an `org` formatted document.
2.  Fill (compute) the blanks with each battle outcome (Day in the Advent Code.
    calendar).
3.  Render the report using Markdown.

Sample answers can be found below behind a strong layer of HTML+Markdown magic
kindly executed by the generous Microsoft robot army.


# Setup

Execute the following to develop and test my repository.

    pip install -r *.txt
    pip install -Ue .


# Battle Reports

<details>
<summary>Day 1</summary>

    python3 aoc/day_01.py

    Executing for sample=['0', '3', '2', '4']
    Depth increments: 2

</details>

<details>
<summary>Day 2</summary>

    python3 aoc/day_02.py

    Executing for instructions=['forward 2', 'down 3', 'up 2'] version='v1'
    Position and depth = (2, 1)
    Executing for instructions=['forward 2', 'down 3', 'up 2'] version='v2'
    Position and depth = (2, 0, 1)

</details>

<details>
<summary>Day 3</summary>

    exec 2>&1
    clojure src/day_03.clj
    :

    Opening data from data/03.txt
    Execution error (FileNotFoundException) at java.io.FileInputStream/open0 (FileInputStream.java:-2).
    data/03.txt (No such file or directory)
    
    Full report at:
    /tmp/clojure-5805642547431748840.edn

</details>

