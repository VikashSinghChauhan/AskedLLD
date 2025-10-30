Filesystem


“I implemented this using the Composite Design Pattern, which treats individual files and directories uniformly via a common interface.
Each node implements FileSystemNode, supporting operations like ls, cd, and pwd.
The Directory class acts as a composite and recursively delegates ls() to its children, while File acts as a leaf and provides a base behavior.
This makes the design open to extension (like adding search, delete, or move) without modifying existing code.”
