/* (c) 2020 Open Source Geospatial Foundation - all rights reserved
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */

package org.geoserver.jackson.databind.catalog.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonTypeName("CoverageInfo")
public class Coverage extends Resource {
    private String nativeFormat;
    private GridGeometryDto grid;
    private List<String> supportedFormats;
    private List<String> interpolationMethods;
    private String defaultInterpolationMethod;
    private List<CoverageDimension> dimensions;
    private List<String> requestSRS;
    private List<String> responseSRS;
    private Map<String, Serializable> parameters;
    private String nativeCoverageName;
}
