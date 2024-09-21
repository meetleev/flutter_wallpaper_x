/* import 'package:flutter_test/flutter_test.dart';
import 'package:wallpaper_x/wallpaper_x.dart';
import 'package:wallpaper_x/wallpaper_x_platform_interface.dart';
import 'package:wallpaper_x/wallpaper_x_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockWallpaper_xPlatform
    with MockPlatformInterfaceMixin
    implements Wallpaper_xPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final Wallpaper_xPlatform initialPlatform = Wallpaper_xPlatform.instance;

  test('$MethodChannelWallpaper_x is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelWallpaper_x>());
  });

  test('getPlatformVersion', () async {
    Wallpaper_x wallpaper_xPlugin = Wallpaper_x();
    MockWallpaper_xPlatform fakePlatform = MockWallpaper_xPlatform();
    Wallpaper_xPlatform.instance = fakePlatform;

    expect(await wallpaper_xPlugin.getPlatformVersion(), '42');
  });
}
 */