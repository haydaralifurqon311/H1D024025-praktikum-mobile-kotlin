import 'package:flutter/material.dart';
import 'ui/produk_page.dart';
// import 'ui/produk_form.dart'; // aktifkan untuk mengetes form

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Aplikasi Flutter Pertama",
      theme: ThemeData(primarySwatch: Colors.blue, useMaterial3: false),
      home: const ProdukPage(),
    );
  }
}