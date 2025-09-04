# Huffman File Compressor

A file compression utility using Huffman Coding. This project analyzes character frequencies in a text file,
builds a Huffman tree, and encodes each character with optimized variable-length binary codes.

*This project was completed as a course assignment from Fall Semester 2025, demonstrating practical application of data structures and
file compression algorithms. Select files were provided by professors as a skeleton for the project.*

---

## Features

- Compress and decompress text files and dat files
- Variable-length binary encoding based on character frequency
- Sample files included for testing/demonstration
- Testing files using the extension: Test Runner for Java

## Project Structure

```
huffman/
├── examples/ # Sample input/output files
│ ├── bytes.dat
│ ├── Empty.txt
│ ├── fibonacci.txt
│ ├── mary.txt
│ ├── OneByte.txt
│ ├── test.dat
│ └── test.txt
├── src/ # Source code
│ ├── BitSequence.java
│ ├── HuffBaseNode.java
│ ├── HuffInternalNode.java
│ ├── HuffLeafNode.java
│ ├── HuffmanSave.java
│ ├── HuffTree.java
│ ├── MadZip.java
│ └── MadZipApp.java
├── tests/ # Test scripts
│ ├── HuffmanNodeTest.java
│ └── HuffTreeTest.java
└── README.md
```

## Skills Demonstrated
- Implementation of Huffman Coding and tree-based algorithms
- Bit-level file I/O and binary data manipulation
- Use of data structures such as trees and priority queues


## Setup and Usage

### **Requirements**
- Java
- A terminal or command prompt

### **Compile**
From the project root (huffman/), run:<br>
```javac -d bin src/*.java```<br>

This compiles the source files into the bin directory.

### **Run**
After compiling, run the program with:<br>
```java -cp bin pas.huffman.src.MadZipApp```<br>

This launches the Swing-based GUI for compressing and decompressing files.

### **Note**
- Sample files are available in the examples/ directory for testing.
