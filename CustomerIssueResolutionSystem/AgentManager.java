package com.example.SpringTest.CustomerIssueResolutionSystem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AgentManager {
    Map<String, Agent> agentPool = new ConcurrentHashMap<>();
    Map<Integer, List<Agent>> expertisemap = new ConcurrentHashMap<>();

    public Boolean exists(String agentId)
    {
        return agentPool.containsKey(agentId);
    }

    public void addAgent(Agent agent){
        agentPool.put(agent.agentId, agent);
        for(Integer type : agent.expertise.keySet())
        {
            expertisemap.computeIfAbsent(type, k->new ArrayList<>()).add(agent);
        }
    }

    public Agent getAgent(String agentId)
    {
        return agentPool.getOrDefault(agentId, null);
    }

    public List<Agent> getAgentsForType(int issueType){
        return expertisemap.getOrDefault(issueType, Collections.emptyList());
    }

    public ArrayList<Agent> getAllAgents(){
        return new ArrayList<>(agentPool.values());
    }
}
