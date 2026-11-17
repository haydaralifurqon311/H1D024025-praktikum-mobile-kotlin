import 'package:flutter/material.dart';

class HelloWorld extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('belajar Flutter'),
      ),
      body: Center(
        child: Text('Hello, World!'),
      ),
    );
  }
}