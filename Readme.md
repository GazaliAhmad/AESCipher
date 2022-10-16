# AES Cipher

A simple implementation of AES with IV and CBC mode. AES is a symmetric block cipher that can encrypt and decrypt data. It is a very popular algorithm and is used in many applications.

## What is CBC mode?

CBC mode is a block cipher mode that allows us to encrypt irregularly-sized messages, despite the fact that a block cipher natively only transforms individual blocks.

## What is IV?

IV is an initialization vector. The initialization vector is a fixed-size input to a cryptographic primitive that is supposed to be chosen at random or pseudo-randomly. It is typically combined with the key and used as an input to a cryptographic function.

## What is PKCS5Padding?

PKCS5Padding is a padding method. It is a padding method that is used to pad a message to a specific block length. The padding is done using the value of the padding required. For example, if the required padding length is 8 bytes and the message length is 10 bytes, then 6 bytes of padding is added, each of which has value 0x06.
