import 'src/pigeon/messages.g.dart';

class WallpaperX {
  final WallpaperXInterface _wallpaperXInterface = WallpaperXInterface();

  /// instance
  factory WallpaperX() => instance;

  /// instance
  static WallpaperX get instance => WallpaperX._();
  WallpaperX._();

  /// setLiveWallpaper
  Future<bool> setLiveWallpaper({required String filePath}) async {
    return _wallpaperXInterface.setLiveWallpaper(filePath);
  }
}
