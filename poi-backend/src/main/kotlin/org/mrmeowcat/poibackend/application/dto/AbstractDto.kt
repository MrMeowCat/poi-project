package org.mrmeowcat.poibackend.application.dto

import org.springframework.hateoas.ResourceSupport

abstract class AbstractDto(var id: String? = null) : ResourceSupport()