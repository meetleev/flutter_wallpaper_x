import 'src/pigeon/messages.g.dart';

class WallpaperX {
  final WallpaperXInterface _wallpaperXInterface = WallpaperXInterface();

  factory WallpaperX() => instance;
  static WallpaperX get instance => WallpaperX._();
  WallpaperX._();

  Future<bool> setLiveWallpaper({required String filePath}) async {
    return _wallpaperXInterface.setLiveWallpaper(filePath);
  }
}
