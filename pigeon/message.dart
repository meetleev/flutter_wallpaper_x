import 'package:pigeon/pigeon.dart';

@ConfigurePigeon(PigeonOptions(
  dartOut: 'lib/src/pigeon/messages.g.dart',
  dartOptions: DartOptions(),
  // cppOptions: CppOptions(namespace: 'pigeon_example'),
  // cppHeaderOut: 'windows/runner/messages.g.h',
  // cppSourceOut: 'windows/runner/messages.g.cpp',
  kotlinOut:
  'android/src/main/kotlin/com/meetleev/wallpaper_x/pigeon/Messages.g.kt',
  // kotlinOptions: KotlinOptions(),
  // javaOut: 'android/src/main/java/com/meetleev/plugins/Pigeon.java',
  // javaOptions: JavaOptions(),
  // swiftOut: 'ios/Classes/Messages.g.swift',
  // swiftOptions: SwiftOptions(),
  // objcHeaderOut: 'macos/Runner/messages.g.h',
  // objcSourceOut: 'macos/Runner/messages.g.m',
  // Set this to a unique prefix for your plugin or application, per Objective-C naming conventions.
  // objcOptions: ObjcOptions(prefix: 'PGN'),
  // copyrightHeader: 'pigeons/copyright.txt',
  // dartPackageName: 'pigeon_example_package',
))



@HostApi()
abstract class WallpaperXInterface {
  @async
  bool setLiveWallpaper(String filePath);
}
