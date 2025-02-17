/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.boot.model.convert.internal;

import org.hibernate.boot.internal.ClassmateContext;
import org.hibernate.boot.model.convert.spi.JpaAttributeConverterCreationContext;
import org.hibernate.resource.beans.spi.ManagedBean;

import jakarta.persistence.AttributeConverter;

/**
 * ConverterDescriptor implementation for cases where we know the
 * AttributeConverter Class.  This is the normal case.
 *
 * @author Steve Ebersole
 */
public class ClassBasedConverterDescriptor extends AbstractConverterDescriptor {
	public ClassBasedConverterDescriptor(
			Class<? extends AttributeConverter<?,?>> converterClass,
			ClassmateContext classmateContext) {
		super( converterClass, null, classmateContext );
	}

	public ClassBasedConverterDescriptor(
			Class<? extends AttributeConverter<?,?>> converterClass,
			Boolean forceAutoApply,
			ClassmateContext classmateContext) {
		super( converterClass, forceAutoApply, classmateContext );
	}

	@Override
	protected ManagedBean<? extends AttributeConverter<?, ?>> createManagedBean(JpaAttributeConverterCreationContext context) {
		//noinspection unchecked
		return (ManagedBean<? extends AttributeConverter<?, ?>>) context.getManagedBeanRegistry().getBean( getAttributeConverterClass() );
	}
}
