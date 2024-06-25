package org.hl7.fhir.r4b.renderers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r4b.context.IWorkerContext.ValidationResult;
import org.hl7.fhir.r4b.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4b.model.CanonicalResource;
import org.hl7.fhir.r4b.model.CodeSystem;
import org.hl7.fhir.r4b.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r4b.model.CodeSystem.PropertyComponent;
import org.hl7.fhir.r4b.model.ConceptMap;
import org.hl7.fhir.r4b.model.ConceptMap.ConceptMapGroupComponent;
import org.hl7.fhir.r4b.model.ConceptMap.SourceElementComponent;
import org.hl7.fhir.r4b.model.ConceptMap.TargetElementComponent;
import org.hl7.fhir.r4b.model.DomainResource;
import org.hl7.fhir.r4b.model.Questionnaire;
import org.hl7.fhir.r4b.model.Resource;
import org.hl7.fhir.r4b.model.StructureDefinition;
import org.hl7.fhir.r4b.model.ValueSet;
import org.hl7.fhir.r4b.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r4b.renderers.utils.BaseWrappers.ResourceWrapper;
import org.hl7.fhir.r4b.renderers.utils.RenderingContext;
import org.hl7.fhir.r4b.renderers.utils.Resolver.ResourceContext;
import org.hl7.fhir.r4b.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r4b.utils.ToolingExtensions;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

/**
 * Rendering framework:
 * 
 * See R5 rendering framework to render R4B resources
 * 
 */
@Deprecated
public abstract class TerminologyRenderer extends ResourceRenderer {

  public TerminologyRenderer(RenderingContext context) {
    super(context);
  }

  public TerminologyRenderer(RenderingContext context, ResourceContext rcontext) {
    super(context, rcontext);
  }

  public String display(Resource r) throws UnsupportedEncodingException, IOException {
    return ((CanonicalResource) r).present();
  }

  public String display(ResourceWrapper r) throws UnsupportedEncodingException, IOException {
    if (r.has("title")) {
      return r.children("title").get(0).getBase().primitiveValue();
    }
    if (r.has("name")) {
      return r.children("name").get(0).getBase().primitiveValue();
    }
    return "??";
  }

  protected class TargetElementComponentWrapper {
    protected ConceptMapGroupComponent group;
    protected TargetElementComponent comp;

    protected TargetElementComponentWrapper(ConceptMapGroupComponent group, TargetElementComponent comp) {
      super();
      this.group = group;
      this.comp = comp;
    }

  }

  public class UsedConceptMap {

    private ConceptMapRenderInstructions details;
    private String link;
    private ConceptMap map;

    public UsedConceptMap(ConceptMapRenderInstructions details, String link, ConceptMap map) {
      super();
      this.details = details;
      this.link = link;
      this.map = map;
    }

    public ConceptMapRenderInstructions getDetails() {
      return details;
    }

    public ConceptMap getMap() {
      return map;
    }

    public String getLink() {
      return link;
    }
  }

  public class ConceptMapRenderInstructions {
    private String name;
    private String url;
    private boolean doDescription;

    public ConceptMapRenderInstructions(String name, String url, boolean doDescription) {
      super();
      this.name = name;
      this.url = url;
      this.doDescription = doDescription;
    }

    public String getName() {
      return name;
    }

    public String getUrl() {
      return url;
    }

    public boolean isDoDescription() {
      return doDescription;
    }
  }

  protected void addMapHeaders(XhtmlNode tr, List<UsedConceptMap> maps)
      throws FHIRFormatError, DefinitionException, IOException {
    for (UsedConceptMap m : maps) {
      XhtmlNode td = tr.td();
      XhtmlNode b = td.b();
      XhtmlNode a = b.ah(getContext().getSpecificationLink() + m.getLink());
      a.addText(m.getDetails().getName());
      if (m.getDetails().isDoDescription() && m.getMap().hasDescription())
        addMarkdown(td, m.getMap().getDescription());
    }
  }

  protected String getHeader() {
    int i = 3;
    while (i <= getContext().getHeaderLevelContext())
      i++;
    if (i > 6)
      i = 6;
    return "h" + Integer.toString(i);
  }

  protected List<TargetElementComponentWrapper> findMappingsForCode(String code, ConceptMap map) {
    List<TargetElementComponentWrapper> mappings = new ArrayList<TargetElementComponentWrapper>();

    for (ConceptMapGroupComponent g : map.getGroup()) {
      for (SourceElementComponent c : g.getElement()) {
        if (c.getCode().equals(code))
          for (TargetElementComponent cc : c.getTarget())
            mappings.add(new TargetElementComponentWrapper(g, cc));
      }
    }
    return mappings;
  }

  protected String getCharForRelationship(TargetElementComponent mapping) {
    if (!mapping.hasEquivalence())
      return "";
    switch (mapping.getEquivalence()) {
    case EQUIVALENT:
      return "~";
    case WIDER:
      return "<";
    case NARROWER:
      return ">";
    case DISJOINT:
      return "!=";
    default:
      return "?";
    }
  }

  protected <T extends Resource> void addCsRef(ConceptSetComponent inc, XhtmlNode li, T cs) {
    String ref = null;
    boolean addHtml = true;
    if (cs != null) {
      ref = (String) cs.getUserData("external.url");
      if (Utilities.noString(ref))
        ref = (String) cs.getUserData("filename");
      else
        addHtml = false;
      if (Utilities.noString(ref)) {
        ref = (String) cs.getUserData("path");
        if (ref != null) {
          addHtml = false;
        }
      }
    }
    String spec = getSpecialReference(inc.getSystem());
    if (spec != null) {
      XhtmlNode a = li.ah(spec);
      a.code(inc.getSystem());
    } else if (cs != null && ref != null) {
      if (addHtml && !ref.contains(".html"))
        ref = ref + ".html";
      ref = context.fixReference(ref);
      XhtmlNode a = li.ah(ref.replace("\\", "/"));
      a.code(inc.getSystem());
    } else {
      li.code(inc.getSystem());
    }
  }

  private String getSpecialReference(String system) {
    if ("http://snomed.info/sct".equals(system))
      return "http://www.snomed.org/";
    if (Utilities.existsInList(system, "http://loinc.org", "http://unitsofmeasure.org",
        "http://www.nlm.nih.gov/research/umls/rxnorm", "http://ncimeta.nci.nih.gov", "http://fdasis.nlm.nih.gov",
        "http://www.radlex.org", "http://www.whocc.no/atc", "http://dicom.nema.org/resources/ontology/DCM",
        "http://www.genenames.org", "http://www.ensembl.org", "http://www.ncbi.nlm.nih.gov/nuccore",
        "http://www.ncbi.nlm.nih.gov/clinvar", "http://sequenceontology.org", "http://www.hgvs.org/mutnomen",
        "http://www.ncbi.nlm.nih.gov/projects/SNP", "http://cancer.sanger.ac.uk/cancergenome/projects/cosmic",
        "http://www.lrg-sequence.org", "http://www.omim.org", "http://www.ncbi.nlm.nih.gov/pubmed",
        "http://www.pharmgkb.org", "http://clinicaltrials.gov", "http://www.ebi.ac.uk/ipd/imgt/hla/"))
      return system;

    return null;
  }

  protected XhtmlNode addTableHeaderRowStandard(XhtmlNode t, boolean hasHierarchy, boolean hasDisplay,
      boolean definitions, boolean comments, boolean version, boolean deprecated, List<PropertyComponent> properties,
      List<String> langs, boolean doLangs) {
    XhtmlNode tr = t.tr();
    if (hasHierarchy) {
      tr.td().b().tx("Lvl");
    }
    tr.td().attribute("style", "white-space:nowrap").b()
        .tx(getContext().getWorker().translator().translate("xhtml-gen-cs", "Code", getContext().getLang()));
    if (hasDisplay) {
      tr.td().b()
          .tx(getContext().getWorker().translator().translate("xhtml-gen-cs", "Display", getContext().getLang()));
    }
    if (definitions) {
      tr.td().b()
          .tx(getContext().getWorker().translator().translate("xhtml-gen-cs", "Definition", getContext().getLang()));
    }
    if (deprecated) {
      tr.td().b()
          .tx(getContext().getWorker().translator().translate("xhtml-gen-cs", "Deprecated", getContext().getLang()));
    }
    if (comments) {
      tr.td().b()
          .tx(getContext().getWorker().translator().translate("xhtml-gen-cs", "Comments", getContext().getLang()));
    }
    if (version) {
      tr.td().b()
          .tx(getContext().getWorker().translator().translate("xhtml-gen-cs", "Version", getContext().getLang()));
    }
    if (properties != null) {
      for (PropertyComponent pc : properties) {
        String display = ToolingExtensions.getPresentation(pc, pc.getCodeElement());
        if (display == null || display.equals(pc.getCode()) && pc.hasUri()) {
          display = getDisplayForProperty(pc.getUri());
          if (display == null) {
            display = pc.getCode();
          }
        }
        tr.td().b()
            .tx(getContext().getWorker().translator().translate("xhtml-gen-cs", display, getContext().getLang()));
      }
    }
    if (doLangs) {
      for (String lang : langs) {
        tr.td().b().addText(describeLang(lang));
      }
    }
    return tr;
  }

  protected String getDisplayForProperty(String uri) {
    if (Utilities.noString(uri)) {
      return null;
    }
    String code = null;
    if (uri.contains("#")) {
      code = uri.substring(uri.indexOf("#") + 1);
      uri = uri.substring(0, uri.indexOf("#"));
    }
    CodeSystem cs = getContext().getWorker().fetchCodeSystem(uri);
    if (cs == null) {
      return null;
    }
    ConceptDefinitionComponent cc = code == null ? null : CodeSystemUtilities.getCode(cs, code);
    return cc == null ? null : cc.getDisplay();
  }

  protected void AddVsRef(String value, XhtmlNode li) {
    Resource res = null;
    if (rcontext != null) {
      BundleEntryComponent be = rcontext.resolve(value);
      if (be != null) {
        res = be.getResource();
      }
    }
    if (res != null && !(res instanceof CanonicalResource)) {
      li.addText(value);
      return;
    }
    CanonicalResource vs = (CanonicalResource) res;
    if (vs == null)
      vs = getContext().getWorker().fetchResource(ValueSet.class, value);
    if (vs == null)
      vs = getContext().getWorker().fetchResource(StructureDefinition.class, value);
    // if (vs == null)
    // vs = context.getWorker().fetchResource(DataElement.class, value);
    if (vs == null)
      vs = getContext().getWorker().fetchResource(Questionnaire.class, value);
    if (vs != null) {
      String ref = (String) vs.getUserData("path");

      ref = context.fixReference(ref);
      XhtmlNode a = li.ah(ref == null ? "?ngen-11?" : ref.replace("\\", "/"));
      a.addText(value);
    } else {
      CodeSystem cs = getContext().getWorker().fetchCodeSystem(value);
      if (cs != null) {
        String ref = (String) cs.getUserData("path");
        ref = context.fixReference(ref);
        XhtmlNode a = li.ah(ref == null ? "?ngen-12?" : ref.replace("\\", "/"));
        a.addText(value);
      } else if (value.equals("http://snomed.info/sct") || value.equals("http://snomed.info/id")) {
        XhtmlNode a = li.ah(value);
        a.tx("SNOMED-CT");
      } else {
        li.addText(value);
      }
    }
  }

  protected String getDisplayForConcept(String system, String version, String value) {
    if (value == null || system == null)
      return null;
    ValidationResult cl = getContext().getWorker().validateCode(
        getContext().getTerminologyServiceOptions().withVersionFlexible(true), system, version, value, null);
    return cl == null ? null : cl.getDisplay();
  }

}