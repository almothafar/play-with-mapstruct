package modules;

import com.google.inject.AbstractModule;


public class OnStartupModule extends AbstractModule {

    @Override
    public void configure() {
        bind(OnStartup.class).asEagerSingleton();
    }
}
