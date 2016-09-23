package griffio.solarsystem;

import com.querydsl.collections.CollQueryFactory;
import griffio.components.SolarSystem;
import griffio.components.DaggerSolarSystem;
import griffio.entity.Satellite;
import griffio.modules.OuterPlanetsModule;
import griffio.modules.TerrestrialPlanetsModule;
import griffio.entity.QSatellite;

import java.util.List;

/***
 * BaseApplication would only be needed when not generating kapt stubs
 */
public abstract class BaseApplication {

  public SolarSystem solarSystem() {
    return DaggerSolarSystem.builder()
        .terrestrialPlanetsModule(new TerrestrialPlanetsModule())
        .outerPlanetsModule(new OuterPlanetsModule())
        .build();
  }

  public Satellite findSatellite(List<Satellite> satellites) {
    return CollQueryFactory
        .from(QSatellite.satellite, satellites)
        .where(QSatellite.satellite.diameter.between(3000.0, 4000.0)).fetchFirst();
  }

}
