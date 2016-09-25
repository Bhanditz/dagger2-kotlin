dagger2-example with Kotlin (1.0.4) annotation processor support Gradle build
=================================

[kapt-annotation-processing-for-kotlin](http://blog.jetbrains.com/kotlin/2015/05/kapt-annotation-processing-for-kotlin)

[better-annotation-processing-supporting-stubs-in-kapt](http://blog.jetbrains.com/kotlin/2015/06/better-annotation-processing-supporting-stubs-in-kapt)

[Implement Annotation Processing API (JSR 269) natively in Kotlin](https://youtrack.jetbrains.com/issue/KT-13499)

[Dagger2 site ](http://google.github.io/dagger/)

Notes:-

This example uses kapt generated stubs. Unless ```generateStubs = true``` is enabled, "bootstrap" Java code is required to reference generated sources.

~~~ groovy
kapt {
  generateStubs = true
}
~~~

Stubs, compiler generated intermediate classes, allows "generated" sources to be referenced from Kotlin otherwise the compiler will not be able to reference the missing sources.

Generated source is created in "build/generated/source/kapt/main", as this is under "build", normally excluded from IntelliJ's project sources, this source root will be marked in the build script itself.

~~~ groovy
sourceSets {
  main.java.srcDirs += [file("$buildDir/generated/source/kapt/main")]
}
~~~

---

* @Component
  * @Module
    * @Provides

Shows Planets being injected via constructor by qualifier

~~~ kotlin
public class TerrestrialPlanets @Inject (@Named("Mercury") val mercury: Planet,
                                         @Named("Venus") val venus: Planet,
                                         @Named("Earth") val earth: Planet,
                                         @Named("Mars") val mars: Planet) {
}
~~~

The TerrestrialPlanetsModule, for example, provides a singleton named "Mercury" etc.

~~~ kotlin
@Module
public class TerrestrialPlanetsModule {

    @Provides @Singleton @Named("Mercury")
    public fun first() : Planet {
        return Mercury()
    }

    @Provides @Singleton @Named("Venus")
    public fun second() : Planet {
        return Venus()
    }

    @Provides @Singleton @Named("Earth")
    public fun third() : Planet {
        return Earth()
    }

    @Provides @Singleton @Named("Mars")
    public fun fourth() : Planet {
        return Mars()
    }

}
~~~

* Outer Planets module
  * Jupiter
  * Saturn
  * Uranus
  * Neptune

---

**Gradle build**

~~~
./gradlew run
~~~
