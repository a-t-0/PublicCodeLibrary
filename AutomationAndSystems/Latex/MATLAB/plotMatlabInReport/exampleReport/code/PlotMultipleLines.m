classdef PlotMultipleLines
   methods
        function plot_altitudes(obj,plotData)
            % plot altitude with legends etc
            nr_of_dimensions = plotData.getNrOfDimensions();
            nr_of_lines_per_dim = plotData.getNrOfLinesPerDim();
            max_nr_of_lines = max(nr_of_lines_per_dim)
            data_series_length = zeros(nr_of_dimensions,max_nr_of_lines)
            dataSeries = plotData.getDataSeries();
            
            % Create vector with data series length
            for dim = 1:nr_of_dimensions
                for line = 1:nr_of_lines_per_dim(dim)
                    disp(length(dataSeries.get(dim-1).get(line-1)))
                    data_series_length(dim,line) =...
                        length(plotData.dataSeries.get(dim-1).get(line-1))
                    disp("Serieslength="+4)
                end
            end
            
            % declare dataseries
%             for  i = 1:nr_of_lines_per_dim
%                 x_series= zeros(nr_of_lines_per_dim,data_series_length(i,1));
%                 y_series= zeros(nr_of_lines_per_dim,data_series_length(i,1));
%                 z_series= zeros(nr_of_lines_per_dim,data_series_length(i,1));
%             end

            % fill x dataseries
%             for i = 1:nr_of_lines_per_dim
%                 x_series(i,:) = 1:data_series_length(i,1)
%             end

            % fill y dataseries
%             y_series(1,:) = est_alt_km;
%             y_series(2,:) = precise_alt_km;
           
            createFigure(plotData)
        end

        
   end
end

function createFigure(plotData)
            % create labels
            labels = plotData.getAxisLabels();

            % create figure object
            f2 = figure;
            figure(2);clf(2);
            hold on

            % plot dataseries
            if (plotData.getNrOfDimensions == 2)
                x_series = plotData.getDataSeries().get(0) % java
                y_series = plotData.getDataSeries().get(1) % java
                for line = 0:plotData.getDataSeries().get(1).size()-1 % java
                    if (line >= plotData.getDataSeries().get(0).size()-1)
                        plot(x_series.get(plotData.getDataSeries().get...
                            (0).size()-1),y_series.get(line)); % java
                    else
                        plot(x_series.get(line),y_series.get(line)); % java
                    end
                end
            end

            % define axis domains and ticks
%             axis([0 0.4 0 1.6]);
%             set(gca, 'XTick',0:0.1:0.4);
%             set(gca, 'XTick',[1,2,3,5,6,7,8,9,10,11]);
            set(gca, 'XTickLabel',[1 2334 3 5 6 7 8 9 10 11]);
%             set(gca, 'YTick',0:0.4:1.6);

%             set xlabel
%             xlabel('$\displaystyle\frac{X{g_0}}{C_{eff}^2}$','Interpreter','latex');
            xlabel('epoch')
            xlh = get(gca,'xlabel');
            gyl = get(xlh);   
            xlp = get(xlh, 'Position');
%             disable rotation of yaxis label
%             set(xlh, 'Rotation',0, 'Position',[0.38 -0.03], 'VerticalAlignment','top', 'HorizontalAlignment','right');

            ylabel('altitude [km]');
%             turn y label into object
            ylh = get(gca,'ylabel');
            gyl = get(ylh);
            ylp = get(ylh, 'Position');
%             disable rotation of yaxis label
%             set(ylh, 'Rotation',0, 'Position',[-0.01 1.5], 'VerticalAlignment','top', 'HorizontalAlignment','right');
% 
%             Add arrows with text. the coordinates are fractions of the axis lengths
%             annotation('textarrow', [0.33 0.275], [0.6 0.662],'String' , 'T=const'); 
%             annotation('textarrow', [0.6 0.52], [0.5 0.562],'String' , '\psi=const'); 
% 
%             Add text at random positions in the graph
%             text(0.001,0.49,'0.6')
%             text(0.03,0.33,'0.6')
%             text(0.1,0.6,'0.4')
%             text(0.02,0.77,'0.4')
%             text(0.11,1.4,'$\displaystyle\frac{M}{M_0}=0.2$','Interpreter','latex')
%             text(0.34,1.28,'$\displaystyle\frac{M}{M_0}=0.2$','Interpreter','latex')
% 
%             add legend to plot
            legend(labels(1,:));

            hold off

            exportFigureToLatex(f2,plotData)
        end

        % suppose you have a folder: "code" and a folder "latex" next to
        % eachother, then you can save to: "parent dir of this code">latex>images>
        function exportFigureToLatex(figureObject,plotData)
            currentFolder = pwd;

            % write the path from the folder that contains "Latex" to this code:
            subtractPath = "/code/";
            %+2 to account for begin and end slash
            parentFolder =currentFolder(1:(length(currentFolder)-strlength(subtractPath)+2));
            targetFolder = parentFolder+"latex/images/";


            destinationFile = targetFolder+filename

            figureObject;

        %     hgexport(gcf, 'figure1.jpg', hgexport('factorystyle'), 'Format', 'jpeg');
        %     hgexport(gcf, destinationFile, hgexport('factorystyle'), 'Format', 'jpeg');
            saveas(gca, destinationFile,'epsc');
        end