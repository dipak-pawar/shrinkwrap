/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.impl.base;

import java.util.logging.Logger;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.container.ResourceAdapterContainer;
import org.jboss.shrinkwrap.impl.base.asset.ClassLoaderAsset;

/**
 * ResourceAdapterContainerBase
 * 
 * Abstract class that helps implement the ResourceAdapter. 
 * Used by specs that extends the ResourceAdapter.
 *
 * @author <a href="mailto:baileyje@gmail.com">John Bailey</a>
 * @version $Revision: $
 * @param <T>
 */
public abstract class ResourceAdapterContainerBase<T extends Archive<T>> extends ContainerBase<T>
      implements
         ResourceAdapterContainer<T>
{
   //-------------------------------------------------------------------------------------||
   // Class Members ----------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   private static final Logger log = Logger.getLogger(ResourceAdapterContainerBase.class.getName());

   //-------------------------------------------------------------------------------------||
   // Constructor ------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   protected ResourceAdapterContainerBase(Class<T> actualType, Archive<?> archive)
   {
      super(actualType, archive);
   }

   //-------------------------------------------------------------------------------------||
   // Required Implementations - ResourceAdapterContainer - Resources --------------------||
   //-------------------------------------------------------------------------------------||

   /* (non-Javadoc)
    * @see org.jboss.declarchive.api.container.RContainer#setApplicationXML(java.lang.String)
    */
   @Override
   public T setResourceAdapterXML(String resourceName) throws IllegalArgumentException
   {
      Validate.notNull(resourceName, "ResourceName must be specified");
      return add(getManinfestPath(), "ra.xml", new ClassLoaderAsset(resourceName));
   }

}
