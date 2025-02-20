package org.hl7.fhir.convertors.conv10_30.resources10_30;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.convertors.SourceElementComponentWrapper;
import org.hl7.fhir.convertors.context.ConversionContext10_30;
import org.hl7.fhir.convertors.conv10_30.VersionConvertor_10_30;
import org.hl7.fhir.convertors.conv10_30.datatypes10_30.complextypes10_30.CodeableConcept10_30;
import org.hl7.fhir.convertors.conv10_30.datatypes10_30.complextypes10_30.ContactPoint10_30;
import org.hl7.fhir.convertors.conv10_30.datatypes10_30.complextypes10_30.Identifier10_30;
import org.hl7.fhir.convertors.conv10_30.datatypes10_30.primitivetypes10_30.Boolean10_30;
import org.hl7.fhir.convertors.conv10_30.datatypes10_30.primitivetypes10_30.Code10_30;
import org.hl7.fhir.convertors.conv10_30.datatypes10_30.primitivetypes10_30.DateTime10_30;
import org.hl7.fhir.convertors.conv10_30.datatypes10_30.primitivetypes10_30.String10_30;
import org.hl7.fhir.convertors.conv10_30.datatypes10_30.primitivetypes10_30.Uri10_30;
import org.hl7.fhir.dstu3.model.ConceptMap;
import org.hl7.fhir.dstu3.model.ConceptMap.ConceptMapGroupComponent;
import org.hl7.fhir.dstu3.model.Enumeration;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.exceptions.FHIRException;

public class ConceptMap10_30 {

  public static org.hl7.fhir.dstu2.model.ConceptMap convertConceptMap(org.hl7.fhir.dstu3.model.ConceptMap src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.ConceptMap tgt = new org.hl7.fhir.dstu2.model.ConceptMap();
    ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyDomainResource(src, tgt);
    if (src.hasUrlElement())
      tgt.setUrlElement(Uri10_30.convertUri(src.getUrlElement()));
    if (src.hasIdentifier())
      tgt.setIdentifier(Identifier10_30.convertIdentifier(src.getIdentifier()));
    if (src.hasVersionElement())
      tgt.setVersionElement(String10_30.convertString(src.getVersionElement()));
    if (src.hasNameElement())
      tgt.setNameElement(String10_30.convertString(src.getNameElement()));
    if (src.hasStatus())
      tgt.setStatusElement(Enumerations10_30.convertConformanceResourceStatus(src.getStatusElement()));
    if (src.hasExperimental())
      tgt.setExperimentalElement(Boolean10_30.convertBoolean(src.getExperimentalElement()));
    if (src.hasPublisherElement())
      tgt.setPublisherElement(String10_30.convertString(src.getPublisherElement()));
    for (org.hl7.fhir.dstu3.model.ContactDetail t : src.getContact())
      tgt.addContact(convertConceptMapContactComponent(t));
    if (src.hasDate())
      tgt.setDateElement(DateTime10_30.convertDateTime(src.getDateElement()));
    if (src.hasDescription())
      tgt.setDescription(src.getDescription());
    for (org.hl7.fhir.dstu3.model.UsageContext t : src.getUseContext())
      if (t.hasValueCodeableConcept())
        tgt.addUseContext(CodeableConcept10_30.convertCodeableConcept(t.getValueCodeableConcept()));
    for (org.hl7.fhir.dstu3.model.CodeableConcept t : src.getJurisdiction())
      tgt.addUseContext(CodeableConcept10_30.convertCodeableConcept(t));
    if (src.hasPurpose())
      tgt.setRequirements(src.getPurpose());
    if (src.hasCopyright())
      tgt.setCopyright(src.getCopyright());
    if (src.hasSource())
      tgt.setSource(ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().convertType(src.getSource()));
    if (src.hasTarget())
      tgt.setTarget(ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().convertType(src.getTarget()));
    for (org.hl7.fhir.dstu3.model.ConceptMap.ConceptMapGroupComponent g : src.getGroup())
      for (org.hl7.fhir.dstu3.model.ConceptMap.SourceElementComponent t : g.getElement())
        tgt.addElement(convertSourceElementComponent(t, g));
    return tgt;
  }

  public static org.hl7.fhir.dstu3.model.ConceptMap convertConceptMap(org.hl7.fhir.dstu2.model.ConceptMap src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu3.model.ConceptMap tgt = new org.hl7.fhir.dstu3.model.ConceptMap();
    ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyDomainResource(src, tgt);
    if (src.hasUrlElement())
      tgt.setUrlElement(Uri10_30.convertUri(src.getUrlElement()));
    if (src.hasIdentifier())
      tgt.setIdentifier(Identifier10_30.convertIdentifier(src.getIdentifier()));
    if (src.hasVersionElement())
      tgt.setVersionElement(String10_30.convertString(src.getVersionElement()));
    if (src.hasNameElement())
      tgt.setNameElement(String10_30.convertString(src.getNameElement()));
    if (src.hasStatus())
      tgt.setStatusElement(Enumerations10_30.convertConformanceResourceStatus(src.getStatusElement()));
    if (src.hasExperimental())
      tgt.setExperimentalElement(Boolean10_30.convertBoolean(src.getExperimentalElement()));
    if (src.hasPublisherElement())
      tgt.setPublisherElement(String10_30.convertString(src.getPublisherElement()));
    for (org.hl7.fhir.dstu2.model.ConceptMap.ConceptMapContactComponent t : src.getContact())
      tgt.addContact(convertConceptMapContactComponent(t));
    if (src.hasDate())
      tgt.setDateElement(DateTime10_30.convertDateTime(src.getDateElement()));
    if (src.hasDescription())
      tgt.setDescription(src.getDescription());
    for (org.hl7.fhir.dstu2.model.CodeableConcept t : src.getUseContext())
      if (VersionConvertor_10_30.isJurisdiction(t))
        tgt.addJurisdiction(CodeableConcept10_30.convertCodeableConcept(t));
      else
        tgt.addUseContext(CodeableConcept10_30.convertCodeableConceptToUsageContext(t));
    if (src.hasRequirements())
      tgt.setPurpose(src.getRequirements());
    if (src.hasCopyright())
      tgt.setCopyright(src.getCopyright());
    if (src.hasSource())
      tgt.setSource(ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().convertType(src.getSource()));
    if (src.hasTarget())
      tgt.setTarget(ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().convertType(src.getTarget()));
    for (org.hl7.fhir.dstu2.model.ConceptMap.SourceElementComponent t : src.getElement()) {
      List<SourceElementComponentWrapper<ConceptMap.SourceElementComponent>> ws = convertSourceElementComponent(t);
      for (SourceElementComponentWrapper<ConceptMap.SourceElementComponent> w : ws)
        getGroup(tgt, w.getSource(), w.getTarget()).addElement(w.getComp());
    }
    return tgt;
  }

  public static org.hl7.fhir.dstu3.model.ContactDetail convertConceptMapContactComponent(org.hl7.fhir.dstu2.model.ConceptMap.ConceptMapContactComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu3.model.ContactDetail tgt = new org.hl7.fhir.dstu3.model.ContactDetail();
    ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyElement(src, tgt);
    if (src.hasNameElement())
      tgt.setNameElement(String10_30.convertString(src.getNameElement()));
    for (org.hl7.fhir.dstu2.model.ContactPoint t : src.getTelecom())
      tgt.addTelecom(ContactPoint10_30.convertContactPoint(t));
    return tgt;
  }

  public static org.hl7.fhir.dstu2.model.ConceptMap.ConceptMapContactComponent convertConceptMapContactComponent(org.hl7.fhir.dstu3.model.ContactDetail src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.ConceptMap.ConceptMapContactComponent tgt = new org.hl7.fhir.dstu2.model.ConceptMap.ConceptMapContactComponent();
    ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyElement(src, tgt);
    if (src.hasNameElement())
      tgt.setNameElement(String10_30.convertString(src.getNameElement()));
    for (org.hl7.fhir.dstu3.model.ContactPoint t : src.getTelecom())
      tgt.addTelecom(ContactPoint10_30.convertContactPoint(t));
    return tgt;
  }

  static public org.hl7.fhir.dstu3.model.Enumeration<org.hl7.fhir.dstu3.model.Enumerations.ConceptMapEquivalence> convertConceptMapEquivalence(org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      Enumeration<Enumerations.ConceptMapEquivalence> tgt = new Enumeration<>(new Enumerations.ConceptMapEquivalenceEnumFactory());
      ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case EQUIVALENT:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.EQUIVALENT);
                  break;
              case EQUAL:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.EQUAL);
                  break;
              case WIDER:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.WIDER);
                  break;
              case SUBSUMES:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.SUBSUMES);
                  break;
              case NARROWER:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.NARROWER);
                  break;
              case SPECIALIZES:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.SPECIALIZES);
                  break;
              case INEXACT:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.INEXACT);
                  break;
              case UNMATCHED:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.UNMATCHED);
                  break;
              case DISJOINT:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.DISJOINT);
                  break;
              default:
                  tgt.setValue(Enumerations.ConceptMapEquivalence.NULL);
                  break;
          }
      }
      return tgt;
  }

  static public org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence> convertConceptMapEquivalence(org.hl7.fhir.dstu3.model.Enumeration<org.hl7.fhir.dstu3.model.Enumerations.ConceptMapEquivalence> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence> tgt = new org.hl7.fhir.dstu2.model.Enumeration<>(new org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalenceEnumFactory());
      ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case EQUIVALENT:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.EQUIVALENT);
                  break;
              case EQUAL:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.EQUAL);
                  break;
              case WIDER:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.WIDER);
                  break;
              case SUBSUMES:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.SUBSUMES);
                  break;
              case NARROWER:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.NARROWER);
                  break;
              case SPECIALIZES:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.SPECIALIZES);
                  break;
              case INEXACT:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.INEXACT);
                  break;
              case UNMATCHED:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.UNMATCHED);
                  break;
              case DISJOINT:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.DISJOINT);
                  break;
              default:
                  tgt.setValue(org.hl7.fhir.dstu2.model.Enumerations.ConceptMapEquivalence.NULL);
                  break;
          }
      }
      return tgt;
  }

  public static org.hl7.fhir.dstu3.model.ConceptMap.OtherElementComponent convertOtherElementComponent(org.hl7.fhir.dstu2.model.ConceptMap.OtherElementComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu3.model.ConceptMap.OtherElementComponent tgt = new org.hl7.fhir.dstu3.model.ConceptMap.OtherElementComponent();
    ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyBackboneElement(src,tgt);
    if (src.hasElementElement())
      tgt.setPropertyElement(Uri10_30.convertUri(src.getElementElement()));
    if (src.hasCodeSystemElement())
      tgt.setSystemElement(Uri10_30.convertUri(src.getCodeSystemElement()));
    if (src.hasCodeElement())
      tgt.setCodeElement(String10_30.convertString(src.getCodeElement()));
    return tgt;
  }

  public static org.hl7.fhir.dstu2.model.ConceptMap.OtherElementComponent convertOtherElementComponent(org.hl7.fhir.dstu3.model.ConceptMap.OtherElementComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.ConceptMap.OtherElementComponent tgt = new org.hl7.fhir.dstu2.model.ConceptMap.OtherElementComponent();
    ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyBackboneElement(src,tgt);
    if (src.hasPropertyElement())
      tgt.setElementElement(Uri10_30.convertUri(src.getPropertyElement()));
    if (src.hasSystemElement())
      tgt.setCodeSystemElement(Uri10_30.convertUri(src.getSystemElement()));
    if (src.hasCodeElement())
      tgt.setCodeElement(String10_30.convertString(src.getCodeElement()));
    return tgt;
  }

  public static List<SourceElementComponentWrapper<ConceptMap.SourceElementComponent>> convertSourceElementComponent(org.hl7.fhir.dstu2.model.ConceptMap.SourceElementComponent src) throws FHIRException {
    List<SourceElementComponentWrapper<ConceptMap.SourceElementComponent>> res = new ArrayList<>();
    if (src == null || src.isEmpty())
      return res;
    for (org.hl7.fhir.dstu2.model.ConceptMap.TargetElementComponent t : src.getTarget()) {
      org.hl7.fhir.dstu3.model.ConceptMap.SourceElementComponent tgt = new org.hl7.fhir.dstu3.model.ConceptMap.SourceElementComponent();
      ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyBackboneElement(src,tgt);
      tgt.setCode(src.getCode());
      tgt.addTarget(convertTargetElementComponent(t));
      res.add(new SourceElementComponentWrapper<>(tgt, src.getCodeSystem(), t.getCodeSystem()));
    }
    return res;
  }

  public static org.hl7.fhir.dstu2.model.ConceptMap.SourceElementComponent convertSourceElementComponent(org.hl7.fhir.dstu3.model.ConceptMap.SourceElementComponent src, org.hl7.fhir.dstu3.model.ConceptMap.ConceptMapGroupComponent g) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.ConceptMap.SourceElementComponent tgt = new org.hl7.fhir.dstu2.model.ConceptMap.SourceElementComponent();
    ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyBackboneElement(src,tgt);
    tgt.setCodeSystem(g.getSource());
    if (src.hasCodeElement())
      tgt.setCodeElement(Code10_30.convertCode(src.getCodeElement()));
    for (org.hl7.fhir.dstu3.model.ConceptMap.TargetElementComponent t : src.getTarget())
      tgt.addTarget(convertTargetElementComponent(t, g));
    return tgt;
  }

  public static org.hl7.fhir.dstu2.model.ConceptMap.TargetElementComponent convertTargetElementComponent(org.hl7.fhir.dstu3.model.ConceptMap.TargetElementComponent src, org.hl7.fhir.dstu3.model.ConceptMap.ConceptMapGroupComponent g) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.ConceptMap.TargetElementComponent tgt = new org.hl7.fhir.dstu2.model.ConceptMap.TargetElementComponent();
    ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyBackboneElement(src,tgt);
    tgt.setCodeSystem(g.getTarget());
    if (src.hasCodeElement())
      tgt.setCodeElement(Code10_30.convertCode(src.getCodeElement()));
    if (src.hasEquivalence())
      tgt.setEquivalenceElement(convertConceptMapEquivalence(src.getEquivalenceElement()));
    if (src.hasCommentElement())
      tgt.setCommentsElement(String10_30.convertString(src.getCommentElement()));
    for (org.hl7.fhir.dstu3.model.ConceptMap.OtherElementComponent t : src.getDependsOn())
      tgt.addDependsOn(convertOtherElementComponent(t));
    for (org.hl7.fhir.dstu3.model.ConceptMap.OtherElementComponent t : src.getProduct())
      tgt.addProduct(convertOtherElementComponent(t));
    return tgt;
  }

  public static org.hl7.fhir.dstu3.model.ConceptMap.TargetElementComponent convertTargetElementComponent(org.hl7.fhir.dstu2.model.ConceptMap.TargetElementComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu3.model.ConceptMap.TargetElementComponent tgt = new org.hl7.fhir.dstu3.model.ConceptMap.TargetElementComponent();
    ConversionContext10_30.INSTANCE.getVersionConvertor_10_30().copyBackboneElement(src,tgt);
    if (src.hasCodeElement())
      tgt.setCodeElement(Code10_30.convertCode(src.getCodeElement()));
    if (src.hasEquivalence())
      tgt.setEquivalenceElement(convertConceptMapEquivalence(src.getEquivalenceElement()));
    if (src.hasCommentsElement())
      tgt.setCommentElement(String10_30.convertString(src.getCommentsElement()));
    for (org.hl7.fhir.dstu2.model.ConceptMap.OtherElementComponent t : src.getDependsOn())
      tgt.addDependsOn(convertOtherElementComponent(t));
    for (org.hl7.fhir.dstu2.model.ConceptMap.OtherElementComponent t : src.getProduct())
      tgt.addProduct(convertOtherElementComponent(t));
    return tgt;
  }

  static public ConceptMapGroupComponent getGroup(ConceptMap map, String srcs, String tgts) {
    for (ConceptMapGroupComponent grp : map.getGroup()) {
      if (grp.getSource().equals(srcs) && grp.getTarget().equals(tgts))
        return grp;
    }
    ConceptMapGroupComponent grp = map.addGroup();
    grp.setSource(srcs);
    grp.setTarget(tgts);
    return grp;
  }
}