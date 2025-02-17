# frozen_string_literal: true
require_relative 'team_position'
require_relative 'team'
class WorldCup
  def initialize
    @groups = {}
  end

  def add_team(group, team)
    @groups[group] = {} unless @groups.key?(group)
    @groups[group][team] = TeamPosition.new(team)
  end

  def to_s
    s = "WorldCup\n"
    @groups.keys.sort.each do |key|
      s += "#{key}\n"
      s += "Team\tP\tGF\tGC\n"
      @groups[key].values.sort.each { |team_position| s += "#{team_position.to_s}\n" }
    end
    s
  end

  def play_match(group, team1, team2, goals1, goals2)
    raise WorldCupError, 'Missing Team' unless @groups.key?(group)
    raise WorldCupError, 'Missing Team' unless @groups[group].key?(team1) && @groups[group].key?(team2)
    @groups[group][team1].match(goals1, goals2)
    @groups[group][team2].match(goals2, goals1)
  end
end
