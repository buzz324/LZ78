"# LZ78" 


LZ78 Compression

Description: Implement LZ78 compression

Specification: Implement the LZ78 compression routines according to the following specifications:

An A solution will have two parts: an encoder, and a decoder. A complete A+ will have two additional parts, a bit-packer, and a bit-unpacker; all separate programs that read standard input and produce standard output.
Your encoder takes its input as a stream of bytes from standard input, and uses the LZ78 algorithm to output pairs, where each pair consists of the number of the longest matching phrase and the value of the first mismatched byte, one pair of space-separated integers per line of output.

The encoder must utilise a multiway trie (not hashing) for the dictionary.
