**LZ78 Compression**

<ins>**Description**</ins>: Implement LZ78 compression

<ins>**Specification**</ins>: Implement the LZ78 compression routines according to the following specifications:

A solution will be a complete encoder which requires followings:
The encoder takes its input as a stream of bytes from standard input,
and uses the LZ78 algorithm to output pairs, where each pair consists of the number of the longest 
matching phrase and the value of the first mismatched byte, one pair of space-separated integers per line of output.

The encoder must utilise a multiway trie (not hashing) for the dictionary.
