package com.anqit.spanqit.core.query;

import java.util.Optional;

import com.anqit.spanqit.core.Spanqit;
import com.anqit.spanqit.core.TriplesTemplate;
import com.anqit.spanqit.graphpattern.GraphName;
import com.anqit.spanqit.graphpattern.TriplePattern;

@SuppressWarnings("unchecked")
abstract class UpdateDataQuery<T extends UpdateDataQuery<T>> extends UpdateQuery<T> {
	protected TriplesTemplate triplesTemplate = Spanqit.triplesTemplate();
	protected Optional<GraphName> graphName = Optional.empty();
	
	protected T addTriples(TriplePattern... triples) {
		triplesTemplate.and(triples);
		
		return (T) this;
	}
	
	protected T setTriplesTemplate(TriplesTemplate triplesTemplate) {
		this.triplesTemplate = triplesTemplate;
		
		return (T) this;
	}
	
	public T graph(GraphName graph) {
		graphName = Optional.ofNullable(graph);
		
		return (T) this;
	}
	
	protected abstract String getPrefix();

	@Override
	protected String getQueryActionString() {
		StringBuilder updateDataQuery = new StringBuilder();

		updateDataQuery.append(getPrefix()).append(" ");
		appendNamedTriplesTemplates(updateDataQuery, graphName, triplesTemplate);

		return updateDataQuery.toString();
	}
}