# DataEncryptionStandard
DES implementation in Java

## TODO
- Key generator
- CBC implementation (currently, only EBC is implemented)

## Description
- The message:
  ```java
  String hex = Bits.stringToHex("Hello World!");
  //hex = 48 65 6C 6C 6F 20 57 6F 72 6C 64 21
  Message msg = new Message(hex);
  
  System.out.println("Message { " + msg.toString() + " }");
  for (int i = 0; i < msg.getBlockCount(); i++) {
      System.out.println(Arrays.toString(msg.getBlock(i)));
  }
  ```
  This produces:
  ```
  Message { Hex: 48656C6C6F20576F726C6421 | Text: Hello World! }
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0]
  [0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1]
  ```
- The key:
  ```java
  Key key = new Key("6D62B676B5F7B932");
  ```
- The DES object:
  ```java
  DES des = new DES();
  ```
- Encryption
  ```java
  Message enc = des.encrypt(msg, key);
  
  System.out.println("Encoded { " + enc.toString() + " }");
  for (int i = 0; i < enc.getBlockCount(); i++) {
      System.out.println(Arrays.toString(enc.getBlock(i)));
  }
  ```
  This produces:
  ```
  Encoded { Hex: FD28DB5FC8D7117DE48C94B4882A07C8 | Text: ý(Û_È×}ä´*È }
  [1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1]
  [1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0]
  ```
- Decryption
  ```java
  Message dec = des.decrypt(enc, key);
  
  System.out.println("Decoded { " + dec.toString() + " }");
  for (int i = 0; i < dec.getBlockCount(); i++) {
      System.out.println(Arrays.toString(dec.getBlock(i)));
  }
  ```
  This produces:
  ```
  Decoded { Hex: 48656C6C6F20576F726C6421 | Text: Hello World! }
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0]
  [0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1]
  ```
