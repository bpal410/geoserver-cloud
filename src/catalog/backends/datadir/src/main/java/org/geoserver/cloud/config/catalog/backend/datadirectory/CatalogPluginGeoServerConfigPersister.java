/*
 * (c) 2022 Open Source Geospatial Foundation - all rights reserved This code is licensed under the
 * GPL 2.0 license, available at the root application directory.
 */
package org.geoserver.cloud.config.catalog.backend.datadirectory;

import org.geoserver.catalog.Info;
import org.geoserver.catalog.event.CatalogModifyEvent;
import org.geoserver.catalog.event.impl.CatalogModifyEventImpl;
import org.geoserver.catalog.impl.DefaultCatalogFacade;
import org.geoserver.catalog.impl.ModificationProxy;
import org.geoserver.catalog.plugin.CatalogPlugin;
import org.geoserver.config.GeoServerConfigPersister;
import org.geoserver.config.util.XStreamPersister;
import org.geoserver.platform.GeoServerResourceLoader;

/**
 * A {@link GeoServerConfigPersister} that unwraps the {@link CatalogModifyEvent#getSource()}'s from
 * a {@link ModificationProxy} before proceeding with {@link
 * GeoServerConfigPersister#handleModifyEvent super.handleModifyEvent()}, since it works only if the
 * source is the real {@link Info}, as thrown by the legacy {@link
 * DefaultCatalogFacade#beforeSaved}, despite it having the following comment: {@code "// TODO:
 * protect this original object, perhaps with another proxy"}; while {@link CatalogPlugin} fixes it
 * both by using the modification proxy as the source and by taking full responsibility of event
 * dispatching instead of mixing it up between catalog and facade.
 */
class CatalogPluginGeoServerConfigPersister extends GeoServerConfigPersister {
    public CatalogPluginGeoServerConfigPersister(GeoServerResourceLoader rl, XStreamPersister xp) {
        super(rl, xp);
    }

    @Override
    public void handleModifyEvent(CatalogModifyEvent event) {
        CatalogModifyEventImpl e = CatalogPluginGeoServerResourcePersister.withRealSource(event);
        super.handleModifyEvent(e);
    }
}
