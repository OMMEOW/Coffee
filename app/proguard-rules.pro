# Room, Hilt, and Compose ship their own consumer ProGuard rules.
# Keep domain models that cross the Room <-> Kotlin reflection boundary.
-keep class com.coffeeshoptycoon.game.data.database.entity.** { *; }
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions
