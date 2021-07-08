package org.hl7.fhir.convertors.conv14_40.datatypes14_40;

import org.hl7.fhir.convertors.VersionConvertorConstants;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.Utilities;

public class Element14_40 {
    static public void copyElement(org.hl7.fhir.dstu2016may.model.Element src, org.hl7.fhir.r4.model.Element tgt, String... exemptExtensions) throws FHIRException {
      if (src.hasId()) tgt.setId(src.getId());
      for (org.hl7.fhir.dstu2016may.model.Extension e : src.getExtension()) {
        if (!Utilities.existsInList(e.getUrl(), exemptExtensions) && (!(e.getUrl().equals(VersionConvertorConstants.PROFILE_EXTENSION) || e.getUrl().equals(VersionConvertorConstants.IG_DEPENDSON_PACKAGE_EXTENSION) || e.getUrl().equals(VersionConvertorConstants.IG_DEPENDSON_VERSION_EXTENSION)))) {
          tgt.addExtension(Extension14_40.convertExtension(e));
        }
      }
    }

    static public void copyElement(org.hl7.fhir.r4.model.Element src, org.hl7.fhir.dstu2016may.model.Element tgt, String... exemptExtensions) throws FHIRException {
      if (src.hasId()) tgt.setId(src.getId());
      for (org.hl7.fhir.r4.model.Extension e : src.getExtension()) {
        if (!Utilities.existsInList(e.getUrl(), exemptExtensions)) {
          tgt.addExtension(Extension14_40.convertExtension(e));
        }
      }
    }
}
