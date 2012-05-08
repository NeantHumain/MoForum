package moforum.adapter

/**
 * Generic adapter trait.
 *
 * @param SourceType The type to adapt from
 * @param DestinationType The type to adapt to
 */
trait Adapter[SourceType, DestinationType] {
	/**
	 * Adapts input from SourceType to DestinationType.
	 *
	 * @param input The source object
	 * @return The destination object
	 */
	def adapt(input: SourceType): DestinationType
}