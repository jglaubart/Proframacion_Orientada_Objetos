# frozen_string_literal: true

require_relative 'pavilion'
class ThemedPavilion < Pavilion
  def initialize(name, max_cap, theme)
    @theme = theme
    super(name, max_cap)
  end

  def to_s
    "#{@theme} Themed #{super}"
  end

  attr_reader :theme, :name

  def to_s
    "#{@theme} Themed #{super}"
  end
end
