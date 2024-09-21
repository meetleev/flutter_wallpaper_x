import 'dart:io';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:path_provider/path_provider.dart';
import 'package:wallpaper_x/wallpaper_x.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final String _videoPath = 'assets/test.mp4';
  final _wallpaperXPlugin = WallpaperX();

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
            child: ElevatedButton(
                onPressed: () => _setLiveWallpaper(),
                child: const Text('Set LiveWallpaper'))),
      ),
    );
  }

  Future<void> _setLiveWallpaper() async {
    final content = await rootBundle.load(_videoPath);
    final buffer = content.buffer.asUint8List();
    final appDir = await getApplicationSupportDirectory();
    String wallpaperPath = '${appDir.path}/wallpaper.mp4';
    File f = File(wallpaperPath);
    await f.writeAsBytes(buffer);
    await _wallpaperXPlugin.setLiveWallpaper(filePath: wallpaperPath);
  }
}
